def call(Map args) {
    def deploymentName = args.deploymentName ?: error("Parameter 'deploymentName' is required.")
    def image = args.image ?: error("Parameter 'image' is required.")

    echo "Updating Kubernetes deployment: ${deploymentName} with image: ${image}"

    // Use kubectl to update the deployment
    sh """
        kubectl set image deployment/${deploymentName} ${deploymentName}=${image} --record
        kubectl rollout status deployment/${deploymentName}
    """
}