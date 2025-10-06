# Testing Your Pokemon API

This guide shows you how to test if your Pokemon API is working correctly, both locally and after deployment.

## Your API Endpoints

Based on your code, your API has these endpoints:

- **GET** `/creatures` - Get all creatures
- **POST** `/creatures` - Add a new creature

## Testing Locally

### 1. Start Your Local Server

```bash
cd pokemon-api
./gradlew run
```

Your server should start on `http://localhost:8080`

### 2. Test with curl Commands

#### Check if server is running:
```bash
curl http://localhost:8080/creatures
```
**Expected response:** `[]` (empty array initially)

#### Add a Pokemon creature:
```bash
curl -X POST http://localhost:8080/creatures \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Pikachu",
    "sprite": 25
  }'
```
**Expected response:** 
```json
{
  "id": 1,
  "name": "Pikachu",
  "sprite": 25
}
```

#### Get all creatures (should now show Pikachu):
```bash
curl http://localhost:8080/creatures
```
**Expected response:**
```json
[
  {
    "id": 1,
    "name": "Pikachu",
    "sprite": 25
  }
]
```

### 3. Test with Browser

Open your browser and go to:
- `http://localhost:8080/creatures` - Should show JSON array of creatures

## Testing on Render (After Deployment)

Replace `your-app-name` with your actual Render app name:

### 1. Basic Health Check
```bash
curl https://your-app-name.onrender.com/creatures
```

### 2. Add a Pokemon
```bash
curl -X POST https://your-app-name.onrender.com/creatures \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Charizard",
    "sprite": 6
  }'
```

### 3. Browser Test
Visit: `https://your-app-name.onrender.com/creatures`

## Testing with Postman

### Import this collection:

1. **GET All Creatures**
   - Method: `GET`
   - URL: `{{base_url}}/creatures`
   
2. **POST New Creature**
   - Method: `POST`
   - URL: `{{base_url}}/creatures`
   - Headers: `Content-Type: application/json`
   - Body (raw JSON):
   ```json
   {
     "name": "Squirtle",
     "sprite": 7
   }
   ```

Set environment variables:
- Local: `base_url = http://localhost:8080`
- Render: `base_url = https://your-app-name.onrender.com`

## What Success Looks Like

### ✅ API is Working When:
- GET `/creatures` returns `200 OK` with JSON array
- POST `/creatures` returns `201 Created` with the new creature
- Response includes auto-generated `id` field
- Content-Type is `application/json`

### ❌ API Problems When:
- Connection refused → Server not running
- 404 Not Found → Wrong URL or route not configured
- 500 Internal Server Error → Check server logs
- Timeout → Server taking too long (common on Render free tier cold starts)

## Render-Specific Testing Notes

### Cold Starts
- Free tier apps "sleep" after 15 minutes of inactivity
- First request after sleep takes 30-60 seconds
- This is normal behavior

### Check Render Logs
1. Go to your Render dashboard
2. Click on your service
3. Go to "Logs" tab to see server output

## Quick Test Script

Save this as `test-api.sh`:

```bash
#!/bin/bash

# Set your base URL
BASE_URL="http://localhost:8080"  # Change to your Render URL when deployed

echo "Testing Pokemon API at $BASE_URL"
echo

# Test 1: Get all creatures (should be empty initially)
echo "1. Getting all creatures:"
curl -s "$BASE_URL/creatures" | jq '.' || curl -s "$BASE_URL/creatures"
echo -e "\n"

# Test 2: Add a Pokemon
echo "2. Adding Pikachu:"
curl -s -X POST "$BASE_URL/creatures" \
  -H "Content-Type: application/json" \
  -d '{"name": "Pikachu", "sprite": 25}' | jq '.' || \
curl -s -X POST "$BASE_URL/creatures" \
  -H "Content-Type: application/json" \
  -d '{"name": "Pikachu", "sprite": 25}'
echo -e "\n"

# Test 3: Get all creatures again (should show Pikachu)
echo "3. Getting all creatures (should include Pikachu):"
curl -s "$BASE_URL/creatures" | jq '.' || curl -s "$BASE_URL/creatures"
echo -e "\n"

echo "API testing complete!"
```

Make it executable and run:
```bash
chmod +x test-api.sh
./test-api.sh
```

## Troubleshooting Common Issues

### Local Issues:
- **Port 8080 in use**: Change port in `application.yaml` or kill other process
- **Gradle not found**: Make sure you're in the `pokemon-api` directory
- **Build fails**: Run `./gradlew clean build` first

### Render Issues:
- **App not responding**: Check logs for errors
- **Very slow first response**: Normal cold start behavior
- **404 errors**: Verify your render.yaml configuration

Your API is working correctly when you can successfully GET and POST creatures! 🚀