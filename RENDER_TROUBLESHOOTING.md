# Render Deployment Troubleshooting Guide

## 🔧 **Issues Fixed**

### ✅ **1. Port Configuration Fixed**
- **Problem**: `application.yaml` was hardcoded to port `8080`
- **Solution**: Changed to `${PORT:8080}` to use Render's dynamic PORT environment variable
- **File**: `pokemon-api/src/main/resources/application.yaml`

### ✅ **2. Dockerfile Optimized**
- **Problem**: Build might fail or be inefficient on Render
- **Solution**: Added `--no-daemon` flag and container-specific Java options
- **File**: `pokemon-api/Dockerfile`

### ✅ **3. render.yaml Location Fixed**
- **Problem**: `render.yaml` was in wrong subdirectory
- **Solution**: Moved to root directory with correct Docker paths
- **File**: `render.yaml` (root level)

### ✅ **4. Environment Variables Fixed**
- **Problem**: PORT was hardcoded
- **Solution**: Set `generateValue: true` so Render assigns dynamic port

## 🚀 **Next Steps**

### **1. Redeploy on Render**
Your fixes are now pushed to GitHub. Render should automatically redeploy, or you can:
1. Go to your Render dashboard
2. Click "Manual Deploy" on your service
3. Wait for deployment to complete

### **2. Test Your API**
Once redeployed, test these URLs (replace `your-app-name` with actual URL):

```bash
# Test root endpoint
curl https://your-app-name.onrender.com/

# Test creatures endpoint  
curl https://your-app-name.onrender.com/creatures

# Add a Pokemon
curl -X POST https://your-app-name.onrender.com/creatures \
  -H "Content-Type: application/json" \
  -d '{"name": "Pikachu", "sprite": 25}'
```

### **3. Check Render Logs**
If still not working:
1. Go to Render dashboard
2. Click on your service
3. Go to "Logs" tab
4. Look for error messages

## 🐛 **Common Error Messages & Fixes**

### **"Application failed to start"**
- **Check**: Logs for Java/Gradle errors
- **Fix**: Verify Dockerfile build process

### **"Connection refused" or 404 errors**
- **Check**: PORT environment variable is being used
- **Fix**: Verify `${PORT:8080}` in application.yaml

### **"Build failed"**
- **Check**: Gradle build errors in logs
- **Fix**: Verify all dependencies are available

### **Cold Start Issues**
- **Symptom**: First request takes 30-60 seconds
- **Solution**: This is normal on Render free tier

## 📊 **Expected Behavior**

### ✅ **Working API Should Return:**
- `GET /` → `"Hello World!"`
- `GET /creatures` → `[]` (empty array initially)
- `POST /creatures` → New Pokemon with auto-generated ID

### ❌ **If Still Not Working:**
1. **Check deployment logs** in Render dashboard
2. **Verify build completed** successfully  
3. **Test locally first** to ensure code works
4. **Check render.yaml paths** are correct

## 🆘 **Need Help?**
If the API is still not working after redeployment:
1. Share your Render app URL
2. Share any error messages from Render logs
3. Test the same endpoints locally to verify they work

Your Pokemon API should now work correctly on Render! 🎉