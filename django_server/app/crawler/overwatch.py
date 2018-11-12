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
        # option.headless = True
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

        for i, hero in enumerate(hero_list[1:2]):
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
            ability_name_list = [i.get_attribute('data-ability-name') for i in
                                 driver.find_elements_by_class_name('ability-showcase-button')]
            ability_icon_list = [i.get_attribute('src') for i in
                                 driver.find_elements_by_css_selector('.ability-showcase-button>.hero-ability-icon')]
            ability_video_list = [i.find_element_by_tag_name('source').get_attribute('src') for i in
                                  driver.find_elements_by_class_name('ability-showcase-video')]

            story_segment = driver.find_elements_by_css_selector('.hero-bio-backstory>p')
            hero_story = ''
            for j, segment in enumerate(story_segment):
                hero_story += segment.get_attribute('innerText')
                if j != len(story_segment) - 1:
                    hero_story += '\n'

            base = driver.find_element_by_css_selector('.base>.hero-bio-copy').get_attribute('innerText')
            base = get_info_after_colon(base)
            affiliation = driver.find_element_by_css_selector('.affiliation>.hero-bio-copy').get_attribute('innerText')
            affiliation = get_info_after_colon(affiliation)

            media = self.crawler_media()

            item['name'] = hero_name
            item['position'] = hero_type
            item['large_avatar'] = large_avatar
            item['description'] = hero_description
            item['story'] = hero_story
            item['hero_bio'] = hero_bio
            item['base'] = base
            item['affiliation'] = affiliation
            item['abilities'] = [
                {'name': ability_name_list[i], 'icon': ability_icon_list[i], 'video': ability_video_list[i]} for i in
                range(0, len(ability_name_list))]
            item['media'] = media
            output.append(item)

            # go back to list page
            driver.close()
            driver.switch_to.window(driver.window_handles[0])

            sleep(8)
        return output

    def crawler_media(self):
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
