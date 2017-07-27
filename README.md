# YFramework


## 使用指南
通过import module导入使用

1、初始化，在app中创建BaseFrameworkInit类
```
public class BaseFrameworkInit implements IBaseRequirement {
    @Override
    public String getUrl() {
        return Constants.API_BASE_URL;  //设置你的api请求根地址
    }

    @Override
    public List<Interceptor> getInterceptors() {
        List<Interceptor> interceptors = new ArrayList<>();
        interceptors.add(new NetInterceptor());
        return interceptors;
    }

    @Override
    public IActivityHelper getActivityHelper() {
        return null;
    }

    @Override
    public IFragmentHelper getFragmentHelper() {
        return null;
    }

    @Override
    public String getImageCacheDir() {
        return "xxxx/image_cache";
    }
}
```

2、在Application中初始化依赖框架的相关服务器地址等
```
    IBaseRequirement requirement = new BaseFrameworkInit();
    if (requirement != null) {
        BaseViewHelper.getInstance().setBaseRequirement(requirement);
        OkHttp3Utils.setInterceptors(requirement.getInterceptors());
        if (!TextUtils.isEmpty(requirement.getUrl()))
             com.turing.framework.utils.Constants.HTTP_URL = requirement.getUrl();
        if (!TextUtils.isEmpty(requirement.getImageCacheDir()))
            com.turing.framework.utils.Constants.IMAGE_CACHE_DIR = requirement.getImageCacheDir();
    }
```

## 使用到的框架(声明)
·[LRecyclerView](https://github.com/jdsjlzx/LRecyclerView)

·Okhttp3

·Rxjava

·Retrofit2


