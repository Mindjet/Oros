from rest_framework import serializers
from app.models import Hero
from app.constant import HERO_FIELDS, BRIEF_HERO_FIELDS


class HeroSerializer(serializers.HyperlinkedModelSerializer):
    class Meta:
        model = Hero
        fields = HERO_FIELDS


class BriefHeroSerializer(serializers.HyperlinkedModelSerializer):
    class Meta:
        model = Hero
        fields = BRIEF_HERO_FIELDS
