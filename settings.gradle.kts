rootProject.name = "sazhinsema"
include("banks-core")
include("banks-console")
include("lab2")
include("lab2:controller")
findProject(":lab2:controller")?.name = "controller"
include("lab2:service")
findProject(":lab2:service")?.name = "service"
include("lab2:dal")
findProject(":lab2:dal")?.name = "dal"
