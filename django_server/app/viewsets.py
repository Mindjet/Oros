from rest_framework import viewsets
from app.models import Hero
from app.serializers import HeroSerializer, BriefHeroSerializer


class HeroViewSet(viewsets.ModelViewSet):
    queryset = Hero.objects.all()
    serializer_class = HeroSerializer


class BriefHeroViewSet(viewsets.ModelViewSet):
    queryset = Hero.objects.all()
    serializer_class = BriefHeroSerializer


class SpecificHeroViewSet(viewsets.ModelViewSet):
    serializer_class = HeroSerializer

    def get_queryset(self):
        id = self.request.GET.get('id')
        queryset = Hero.objects.filter(id=id)
        return queryset
