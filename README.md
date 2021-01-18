[![Jitpack package repository - Bases-Android](https://jitpack.io/v/KhairoHumsi/Bases.svg)](https://jitpack.io/#KhairoHumsi/Bases)


# Android library for Bases classes

Useful library to help Android developers handle databinding, adapters and paging.

## ✨ Supporting

⭐ Star this repository to support this project. You will contribute to increase the visibility of this library 🙂

## Table of contents

- [Android version](#android-version)
- [Introduction](#introduction)
- [Installation](#installation)
- [Using](#using)
  - [BaseActivity](#baseActivity)
  - [BaseLocalizationActivity](#baseLocalizationActivity)
  - [BaseFragment](#baseFragment)
  - [BaseDialogFragment](#baseDialogFragment)
- [Contributing](#contributing)


## Android version

Developed for SDK version 21 (Android 5 Lollipop) and above.


## Introduction

I have created this library because any android developers are using activities, fragments adapters, and paging.
So I decided to create this library to make this work more faster and easier.

**⚠ WARNING ⚠** : I'm using databinding with all of the bases classes in this library, so to use it you must be familiar with it.


## Installation

**Step 1.** Add the [JitPack](https://jitpack.io/#KhairoHumsi/Bases/0.0.4) repository to your build file. Add it in your root `/build.gradle` at the end of repositories:

```gradle
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

**Step 2.** Add the dependency in `/app/build.gradle` :

```gradle
dependencies {
    ...
     implementation 'com.github.KhairoHumsi:Bases:0.0.4'
}
```
## Using

### BaseActivity
**Step 1.** Change your xml layout `viewGroup` from any `viewGroup` to `layout` like bellow:

```xml
<?xml version="1.0" encoding="utf-8"?>
<layout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools">

    <androidx.constraintlayout.widget.ConstraintLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        tools:context=".MainActivity">
        
    </androidx.constraintlayout.widget.ConstraintLayout>
</layout>
```

**Step 2.** Change the `AppCompatActivity` to `BaseActivity` like bellow:

```kotlin
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
}
```


### BaseLocalizationActivity

The difference between `BaseActivity` and `BaseLocalizationActivity` is that weh you want to use localization in your activity you have to use `BaseLocalizationActivity`.

But to use it you have to add [akexorcist/Localization dependency](https://github.com/akexorcist/Localization) which written by [Akexorcist](https://github.com/akexorcist).

**Step 1.** Add the dependency in `/app/build.gradle` :

```gradle
dependencies {
    ...
     implementation 'com.akexorcist:localization:1.2.6'
}
```
**Step 2.** Change your xml layout `viewGroup` from any `viewGroup` to `layout` like bellow:

```xml
<?xml version="1.0" encoding="utf-8"?>
<layout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools">

    <androidx.constraintlayout.widget.ConstraintLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        tools:context=".MainActivity">
        
    </androidx.constraintlayout.widget.ConstraintLayout>
</layout>
```

**Step 3.** Change the `AppCompatActivity` to `BaseLocalizationActivity` like bellow:

```kotlin
class MainActivity : BaseLocalizationActivity<ActivityMainBinding>(R.layout.activity_main) {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
}
```


### BaseFragment
**Step 1.** Change your xml layout `viewGroup` from any `viewGroup` to `layout` like bellow:

```xml
<?xml version="1.0" encoding="utf-8"?>
<layout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools">

    <androidx.constraintlayout.widget.ConstraintLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        tools:context=".BlankFragment">

    </androidx.constraintlayout.widget.ConstraintLayout>
</layout>

```

**Step 2.** Change the `Fragment` to `BaseFragment` like bellow:

```kotlin
class BlankFragment : BaseFragment<FragmentBlankBinding>(R.layout.fragment_blank) {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        
    }
}
```


### BaseDialogFragment
**Step 1.** Change your xml layout `viewGroup` from any `viewGroup` to `layout` like bellow:

```xml
<?xml version="1.0" encoding="utf-8"?>
<layout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools">

    <androidx.constraintlayout.widget.ConstraintLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        tools:context=".dialogFragment">

    </androidx.constraintlayout.widget.ConstraintLayout>
</layout>


```

**Step 2.** Change the `DialogFragment` to `BaseDialogFragment` like bellow:

```kotlin
class DialogFragment : BaseDialogFragment<FragmentDialogBinding>(R.layout.fragment_dialog) {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        
    }
}
```


## Contributing

Please fork this repository and contribute back using pull requests.

Any contributions, large or small, major features, bug fixes, are welcomed and appreciated but will be thoroughly reviewed
