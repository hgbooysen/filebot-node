import groovy.json.*

// get download count from GitHub API
def json = new JsonSlurper().parse(new URL('https://api.github.com/repos/filebot/filebot-node/releases'))

def stats = [
	download_count: json.assets.download_count.flatten().sum() ?: 0
]

println stats

// export stats to ant build
stats.each{ p, v -> project.setProperty(p, v as String) }
