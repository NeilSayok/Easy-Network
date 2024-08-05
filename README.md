# Easy Network

Library to easily impliment Error Handling and Api Response States with Retrofit.

### Downloading:

1: Add it in your root build.gradle at the end of repositories:

```gradle
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        mavenCentral()
        maven { url 'https://jitpack.io' }
    }
}
```

For Gradle Groovy DSL:

```gradle
maven { url 'https://jitpack.io' }
```

For Gradle Kotlin DSL:

```gradle.kts
maven { url = uri("https://jitpack.io") }
```

2: Add the dependency:

For Gradle Groovy DSL:
```gradle
ext {
    version_number = '1.0.1'
}

dependencies {
    implementation "com.github.NeilSayok:Easy-Network:${version_number}"
}
```

For Gradle Kotlin DSL:
```gradle.kts
dependencies {
    val version_number = "1.0.1"
    implementation( "com.github.NeilSayok:Easy-Network:$version_number")
}
```

Usage:

Exception Handling:

```kotlin
 val exceptionHandler = ExceptionHandlerFlow(
            viewModelScope, // Coroutine Scope
            testFlow, // Any flow data Ex:val testFlow = MutableStateFlow<Resource<TestRes>?>(Resource.Idle()) you can send one or more flows in this pararameter seperated by ','

        )
```

`ExceptionHandlerFlow` returns `CoroutineExceptionHandler` which can be used with any dispatchers.

API Response States:

API Response States comes inside Resouces Class:

The below states available representing:

| State Name | Purpose | Extention Function |
|---|---|---|
| Success | Use this State when api Response has been recieved | Resource<T>?.toSuccess(data : T) |
| Error | Use this State when api has thrown any error 4xx, 5xx etc.  | Resource<T>?.toLoading() |
| Loading | Use this State when api is loading  | Resource<T>?.toError(message: String, errorData: ErrorData?) |
| NetworkDisconnected | This is also an error state, automatically thrown by the exception handler when no network is available  | Resource<T>?.toNetworkDisconnected() |
| Idle | Use this State when the response is not required or data needs to be cleared and Response needs not to be in any state | Resource<T>?.toIdle() |





