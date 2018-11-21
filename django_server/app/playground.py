import json

from django.core import serializers
from django.http import HttpResponse

from app.crawler import overwatch
from app.models import Hero, Ability, Media


def _save_hero_in_database(hero_item, _id):
    hero = Hero()
    hero.id = _id
    hero.name = hero_item['name']
    hero.description = hero_item['description']
    hero.affiliation = hero_item['affiliation']
    hero.large_avatar = hero_item['large_avatar']
    hero.base_of_operation = hero_item['base_of_operation']
    hero.bio = hero_item['bio']
    hero.position = hero_item['position']
    hero.story = hero_item['story']
    hero.save()
    for ability_item in hero_item['abilities']:
        ability = Ability.objects.create(
            name=ability_item['name'],
            description=ability_item['description'],
            icon=ability_item['icon'],
            video=ability_item['video']
        )
        hero.ability_set.add(ability)
    for media_item in hero_item['media']:
        media = Media.objects.create(
            name=media_item['name'],
            thumbnail=media_item['thumbnail'],
            type=media_item['type'],
            source=media_item['source']
        )
        hero.media_set.add(media)
    hero.save()


def crawler_overwatch_hero(_):
    crawler = overwatch.OverwatchCrawler()
    result = crawler.get_hero_details()
    crawler.finish()
    for index, hero in enumerate(result):
        _save_hero_in_database(hero, index)
    result = json.dumps(result, ensure_ascii=False)
    return HttpResponse(result, content_type='application/json')


def show_heroes(_):
    heroes = Hero.objects.order_by('id')
    heroes = serializers.serialize('json', heroes, ensure_ascii=False)
    return HttpResponse(heroes, content_type='application/json')


def search_hero(_, _id):
    hero = Hero.objects.filter(id=_id)
    hero = serializers.serialize('json', hero, ensure_ascii=False)
    return HttpResponse(hero, content_type='application/json')
