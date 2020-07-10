"""
 Loads lists with official header fields names from https://www.iana.org/assignments/message-headers/message-headers.xml
 Extract field name, rfc and information if field is obsoleted.

 Since information in RFC is too formal, retrieves user friendly description from
 https://developer.mozilla.org/en-US/docs/Web/HTTP/Headers/

 Note:  wiki pages from https://developer.mozilla.org are distributed under CC-BY-SA licence therefore
 it's important to add attribution to the documentation.
"""

import sys
import csv
import json
import os
from urllib.request import urlretrieve


def load_description(url):
    if not url.startswith("http"):
        return ""

    from urllib.request import Request, urlopen
    req = Request(url)
    try:
        desc = urlopen(req).read().decode("utf8")
        desc = desc.replace("<a href=\"/", "<a href=\"https://developer.mozilla.org/")
        return desc
    except:
        return ""


def rfc_url(str):
    start = str.rfind("[")
    if start >= 0:
        str = str[start + 1:]
    if str.endswith("]"):
        str = str[:-1]

    if not str.startswith("RFC"):
        return ("", "")

    sep = str.find(",")
    rfc = str[3:sep] if sep > 0 else str[3:]
    title = "[RFC" + rfc

    if sep > 0:
        section_start = str.find("Section ", sep)
        section = str[section_start + len("Section "):] if section_start >= 0 else ""
        if len(section) > 0:
            rfc = rfc + "#section-" + section
            title = title + ", Section " + section

    title = title + "]"
    return (rfc, title) if check_url("https://tools.ietf.org/html/rfc" + rfc) else ("", "")


def check_url(url):
    if not url.startswith("http"):
        return False

    from urllib.request import Request, urlopen
    req = Request(url)
    try:
        return urlopen(req).getcode() == 200
    except:
        return False


if len(sys.argv) < 3:
    print("Params error")
    exit(1)

file_name = sys.argv[1]
file_path = sys.argv[2]

headers = []
files = ['perm-headers.csv', 'prov-headers.csv']
for file in files:
    print("Retrieving ", file)
    urlretrieve("https://www.iana.org/assignments/message-headers/" + file, file)
    read = list(csv.reader(open(file)))
    headers = headers + list(csv.reader(open(file)))
    os.remove(file)

print(type(headers), len(headers), headers)
http_headers = [header for header in headers if header[2] == 'http']
print(type(http_headers), len(http_headers), http_headers)

out = []
for header in http_headers:
    item = {
        "name": header[0]
    }

    if header[3] == 'obsoleted' or header[3] == 'deprecated':
        item["obsolete"] = True

    rfc, title = rfc_url(header[4])
    if len(rfc) > 0 and len(title) > 0:
        item["rfc-title"] = title
        item["rfc-ref"] = rfc

    url = "https://developer.mozilla.org/en-US/docs/Web/HTTP/Headers/" + header[0]
    desc = load_description(url + "?summary&raw") if check_url(url) else ""
    print(header)
    print(desc)

    if len(desc) > 0:
        item["descr"] = desc

    out.append(item)

json_data = json.dumps("out", sort_keys=True)
print(json_data)

with open(file_path + "/" + file_name, 'w') as outfile:
    json.dump(out, outfile, sort_keys=True)
