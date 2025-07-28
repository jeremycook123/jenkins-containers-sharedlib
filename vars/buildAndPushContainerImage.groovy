def call(Map args) {
    def image = args.image ?: error("Parameter 'image' is required.")

    // Parse image name and tag
    def imageParts = image.split(':')
    def imageName = imageParts[0]
    def imageTag = imageParts.size() > 1 ? imageParts[1] : 'latest'

    // Assuming Docker is logged in and Dockerfile is in workspace root
    echo "Building Docker image: ${image}"
    sh """
        docker build -t ${imageName}:${imageTag} .
        docker tag ${imageName}:${imageTag} myregistry.com/${imageName}:${imageTag}
        docker push myregistry.com/${imageName}:${imageTag}
    """
}
