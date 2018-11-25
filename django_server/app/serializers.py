from rest_framework import serializers
from app.models import Hero, Ability, Media
from app.constant import HERO_FIELDS, BRIEF_HERO_FIELDS


class AbilitySerializer(serializers.ModelSerializer):
    class Meta:
        model = Ability
        fields = ('name', 'description', 'icon', 'video')


class MediaSerializer(serializers.ModelSerializer):
    class Meta:
        model = Media
        fields = ('name', 'type', 'source', 'thumbnail')


class HeroSerializer(serializers.ModelSerializer):
    ability_set = AbilitySerializer(many=True, read_only=True)
    media_set = MediaSerializer(many=True, read_only=True)

    class Meta:
        model = Hero
        fields = HERO_FIELDS


class BriefHeroSerializer(serializers.HyperlinkedModelSerializer):
    class Meta:
        model = Hero
        fields = BRIEF_HERO_FIELDS
