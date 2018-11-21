import os
from time import sleep

from selenium import webdriver

from app.crawler.functions import find_img_url_in_style

_base_url = 'https://ow.blizzard.cn/heroes/'
_base_avatar_url = 'https://ow.blizzard.cn/heroes/ashe'

_driver_path = os.path.join(os.path.dirname(__file__), 'driver', 'chromedriver.exe')


def get_info_after_colon(info):
    return info.split('：')[1]


class OverwatchCrawler(object):
    def __init__(self):
        option = webdriver.ChromeOptions()
        option.headless = True
        self.driver = webdriver.Chrome(executable_path=_driver_path, chrome_options=option)
        self.driver.implicitly_wait(5)

    def start(self, url):
        self.driver.get(url)
        return self.driver

    def get_hero_avatars(self):
        driver = self.start(_base_avatar_url)
        hero_wrapper_list = driver.find_elements_by_class_name('image')
        hero_avatar_url = [item.get_attribute('style') for item in hero_wrapper_list]
        hero_avatar_url = [find_img_url_in_style(x) for x in hero_avatar_url]
        return hero_avatar_url

    def get_hero_details(self):
        driver = self.start(_base_url)
        output = []
        hero_list = driver.find_elements_by_class_name('hero-portrait-detailed')
        driver.maximize_window()

        count = len(hero_list)
        print('Total heroes: %d' % count)

        for i, hero in enumerate(hero_list):
            print('\rProcessing [%d/%d]' % (i + 1, count), end='', flush=True)
            item = {}
            large_avatar = hero.find_element_by_class_name('portrait').get_attribute('src')

            # go to detail page
            hero.click()
            sleep(6)
            driver.switch_to.window(driver.window_handles[1])

            hero_name = driver.find_element_by_class_name('hero-name').get_attribute('innerText')
            hero_type = driver.find_element_by_class_name('hero-detail-role-name').get_attribute('innerText')
            hero_description = driver.find_element_by_class_name('hero-detail-description').get_attribute('innerText')
            hero_bio = driver.find_element_by_css_selector(
                '#story>.hero-detail-wrapper>p.hero-detail-title').get_attribute('innerText')

            base_of_operation = driver.find_element_by_css_selector('.base>.hero-bio-copy').get_attribute('innerText')
            base_of_operation = get_info_after_colon(base_of_operation)
            affiliation = driver.find_element_by_css_selector('.affiliation>.hero-bio-copy').get_attribute('innerText')
            affiliation = get_info_after_colon(affiliation)

            hero_story = self.crawl_story()
            abilities = self.crawl_abilities()
            media = self.crawl_media()

            item['name'] = hero_name
            item['position'] = hero_type
            item['large_avatar'] = large_avatar
            item['description'] = hero_description
            item['story'] = hero_story
            item['bio'] = hero_bio
            item['base_of_operation'] = base_of_operation
            item['affiliation'] = affiliation
            item['abilities'] = abilities
            item['media'] = media
            output.append(item)

            # go back to list page
            driver.close()
            driver.switch_to.window(driver.window_handles[0])

            sleep(8)
        return output

    def crawl_story(self):
        driver = self.driver
        story_segment = driver.find_elements_by_css_selector('.hero-bio-backstory>p')
        story = ''
        for j, segment in enumerate(story_segment):
            story += segment.get_attribute('innerText')
            if j != len(story_segment) - 1:
                story += '\n'
        return story

    def crawl_abilities(self):
        driver = self.driver
        name_list = [i.get_attribute('data-ability-name') for i in
                     driver.find_elements_by_class_name('ability-showcase-button')]
        icon_list = [i.get_attribute('src') for i in
                     driver.find_elements_by_css_selector('.ability-showcase-button>.hero-ability-icon')]
        video_list = [i.get_attribute('src') for i in
                      driver.find_elements_by_css_selector('video.ability-showcase-video>source')]
        description_list = [i.get_attribute('innerText') for i in
                            driver.find_elements_by_css_selector('div.hero-ability>div.hero-ability-descriptor>p')]
        result = []
        for i in range(0, len(name_list)):
            if i == 0:
                description = ''
            else:
                description = description_list[i-1]
            result.append({
                'name': name_list[i],
                'description': description,
                'icon': icon_list[i],
                'video': video_list[i]
            })
        return result

    def crawl_media(self):
        driver = self.driver
        load_more_btn = driver.find_element_by_id('load-more-media')
        load_more_btn.click()
        sleep(1)
        result = []
        media = driver.find_elements_by_css_selector('ul.media-gallery>li.media-item>div.media-border>a.media-content')
        for item in media:
            thumbnail = find_img_url_in_style(item.get_attribute('style'))
            name = item.get_attribute('title')
            raw_type = item.get_attribute('class').split(' ')[-1]
            if raw_type == 'm-video':
                media_type = 'video'
            elif raw_type == 'm-img':
                media_type = 'image'
            else:
                continue
            item.click()
            sleep(1)
            if media_type == 'image':
                target = driver.find_element_by_css_selector('div.lightbox-container>div.lightbox-body>a.media-item')
                source = target.get_attribute('href')
            else:
                target = driver.find_element_by_css_selector(
                    'div.lightbox-container>div.lightbox-body>div.lightbox-content>video>source')
                source = target.get_attribute('src')
            close_btn = driver.find_element_by_css_selector('div.lightbox-close')
            sleep(2)
            close_btn.click()
            sleep(1)
            result.append({
                'name': name,
                'thumbnail': thumbnail,
                'type': media_type,
                'source': source
            })
        return result

    def go_to_detail(self):
        pass

    def finish(self):
        self.driver.close()
