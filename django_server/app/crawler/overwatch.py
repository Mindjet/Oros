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
        for hero in hero_list[1:2]:
            item = {}
            large_avatar = hero.find_element_by_class_name('portrait').get_attribute('src')

            # go to detail page
            hero.click()
            sleep(2)
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
            for i, segment in enumerate(story_segment):
                hero_story += segment.get_attribute('innerText')
                if i != len(story_segment) - 1:
                    hero_story += '\n'

            base = driver.find_element_by_css_selector('.base>.hero-bio-copy').get_attribute('innerText')
            base = get_info_after_colon(base)
            affiliation = driver.find_element_by_css_selector('.affiliation>.hero-bio-copy').get_attribute('innerText')
            affiliation = get_info_after_colon(affiliation)

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
            output.append(item)

            # go back to list page
            driver.close()
            driver.switch_to.window(driver.window_handles[0])

            # sleep(4)
        return output

    def go_to_detail(self):
        pass

    def finish(self):
        self.driver.close()
