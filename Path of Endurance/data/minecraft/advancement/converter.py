import glob, json


for path in glob.glob("*/*.json"):
    with open(path) as f:
        data = json.load(f)
    if not "rewards" in data:
        data["rewards"] = {}
    data["rewards"]["function"] = "pack:advancement"
    with open(path, "w") as f:
        json.dump(data, f)
