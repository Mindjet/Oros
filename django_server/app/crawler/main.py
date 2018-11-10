from overwatch import OverwatchCrawler

if __name__ == '__main__':
    crawler = OverwatchCrawler()
    heroes = crawler.get_hero_details()
    for hero in heroes:
        print(hero)
    crawler.finish()
