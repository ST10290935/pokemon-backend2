# Deploying Pokemon Backend to Render

This guide will help you deploy your Kotlin Ktor Pokemon backend to Render.

## Prerequisites

1. A GitHub account with your code pushed to a repository
2. A Render account (free tier available at [render.com](https://render.com))

## Deployment Steps

### 1. Prepare Your Repository

Make sure your code is pushed to GitHub with all the configuration files:
- `render.yaml` (deployment configuration)
- `Dockerfile` (container configuration)
- `Dockerfile.alternative` (backup option)
- Updated `application.yaml` (with dynamic port support)

### 2. Connect to Render

1. Go to [render.com](https://render.com) and sign up/login
2. Click "New +" and select "Web Service"
3. Connect your GitHub account and select your repository
4. Choose the branch you want to deploy (usually `main`)

### 3. Configure the Service

Render should automatically detect your `render.yaml` file. If not, configure manually:

- **Name**: `pokemon-api` (or your preferred name)
- **Environment**: `Docker`
- **Build Command**: (leave empty - Docker handles this)
- **Start Command**: (leave empty - Docker handles this)
- **Plan**: Free (for testing)

### 4. Environment Variables

Render will automatically set the `PORT` environment variable. Your app is configured to use it via:
```yaml
port: ${PORT:8080}
```

### 5. Deploy

1. Click "Create Web Service"
2. Render will automatically:
   - Pull your code from GitHub
   - Build the Docker image
   - Deploy your application
   - Provide you with a public URL

### 6. Access Your API

Once deployed, you'll get a URL like: `https://your-app-name.onrender.com`

Your API endpoints will be available at:
- `https://your-app-name.onrender.com/creatures`
- Any other routes you've defined

## Troubleshooting

### If the fat JAR build fails:

1. Replace `Dockerfile` with `Dockerfile.alternative`:
   ```bash
   mv Dockerfile Dockerfile.fatjar
   mv Dockerfile.alternative Dockerfile
   ```

2. Update `render.yaml` to expose the PORT properly:
   ```yaml
   services:
     - type: web
       name: pokemon-api
       env: docker
       plan: free
       dockerfilePath: ./Dockerfile
       dockerContext: ./
   ```

### Common Issues:

1. **Port binding errors**: Make sure your `application.yaml` uses `${PORT:8080}`
2. **Build timeouts**: The free tier has limited build time. Consider optimizing your Docker build
3. **Memory issues**: Free tier has 512MB RAM limit

### Logs and Debugging:

- View logs in the Render dashboard
- Check the "Events" tab for deployment status
- Use the "Shell" tab to access your container

## Automatic Deployments

Render will automatically redeploy when you push to your connected branch. This enables continuous deployment.

## Custom Domain (Optional)

You can add a custom domain in the Render dashboard under "Settings" > "Custom Domains".

## Scaling

- Free tier: Limited resources, sleeps after 15 minutes of inactivity
- Paid tiers: Always available, more resources, faster builds

## API Testing

Test your deployed API:
```bash
curl https://your-app-name.onrender.com/creatures
```

Your Pokemon backend should now be live and accessible from anywhere!