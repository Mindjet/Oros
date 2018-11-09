from selenium import webdriver

from functions import find_img_url_in_style

_base_url = ''
_base_avatar_url = 'https://ow.blizzard.cn/heroes/ashe'


class OverwatchCrawler(object):
    def __init__(self):
        self.driver = webdriver.Chrome(executable_path='./driver/chromedriver.exe')

    def get_hero_avatars(self):
        driver = self.driver
        driver.get(_base_avatar_url)
        hero_wrapper_list = driver.find_elements_by_class_name('image')
        hero_avatar_url = [item.get_attribute('style') for item in hero_wrapper_list]
        hero_avatar_url = [find_img_url_in_style(x) for x in hero_avatar_url]
        print(hero_avatar_url)
        driver.close()
