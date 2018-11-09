import re


def find_img_url_in_style(style):
    img = re.findall(r'(?<=background-image: url\(\").+?(?=\"\))', style)
    if len(img) >= 1:
        return img
    return ''
