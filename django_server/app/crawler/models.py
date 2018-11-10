class Hero(object):
    def __init__(self, name, **kwargs):
        self.name = name
        self.abilities = kwargs.get('abilities')
        pass


class Ability(object):
    def __init__(self, name, description, icon_url, video_url):
        self.name = name
        self.description = description
        self.icon_url = icon_url
        self.video_url = video_url
