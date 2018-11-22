from rest_framework import viewsets
from app.models import Hero
from app.serializers import HeroSerializer, BriefHeroSerializer


class HeroViewSet(viewsets.ModelViewSet):
    queryset = Hero.objects.all()
    serializer_class = HeroSerializer


class BriefHeroViewSet(viewsets.ModelViewSet):
    queryset = Hero.objects.all()
    serializer_class = BriefHeroSerializer
