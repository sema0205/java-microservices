rootProject.name = "sazhinsema"
include("banks-core")
include("banks-console")
include("lab2")
include("lab2:web")
findProject(":lab2:web")?.name = "web"
include("lab2:service")
findProject(":lab2:service")?.name = "service"
include("lab2:dal")
findProject(":lab2:dal")?.name = "dal"
include("lab2:domain")
findProject(":lab2:domain")?.name = "domain"
