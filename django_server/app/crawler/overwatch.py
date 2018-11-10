from time import sleep

from selenium import webdriver

from functions import find_img_url_in_style

_base_url = 'https://ow.blizzard.cn/heroes/'
_base_avatar_url = 'https://ow.blizzard.cn/heroes/ashe'


class OverwatchCrawler(object):
    def __init__(self):
        self.driver = webdriver.Chrome(executable_path='./driver/chromedriver.exe')
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
        large_avatars = []
        hero_list = driver.find_elements_by_class_name('hero-portrait-detailed')
        for hero in hero_list[0:3]:
            large_avatar = hero.find_element_by_class_name('portrait').get_attribute('src')
            large_avatars.append(large_avatar)
            hero.click()
            sleep(2)
            driver.switch_to.window(driver.window_handles[1])
            hero_name = driver.find_element_by_class_name('hero-name').text
            ability_name_list = [i.get_attribute('data-ability-name') for i in
                                 driver.find_elements_by_class_name('ability-showcase-button')]
            ability_icon_list = [i.get_attribute('src') for i in
                                 driver.find_elements_by_css_selector('.ability-showcase-button>.hero-ability-icon')]
            ability_video_list = [i.find_element_by_tag_name('source').get_attribute('src') for i in
                                  driver.find_elements_by_class_name('ability-showcase-video')]
            print(hero_name)
            print(ability_name_list)
            print(ability_icon_list)
            print(ability_video_list)
            driver.close()
            driver.switch_to.window(driver.window_handles[0])
            # abilities = []
            # for i in range(0, len(ability_icon_list)):
            #     abilities.append(Ability(ability_name_list[i], '', ability_icon_list[i], ability_video_list[i]))
            # output.append(Hero(hero_name, abilities=abilities))
            sleep(4)
        return output

    def go_to_detail(self):
        pass

    def finish(self):
        self.driver.close()
