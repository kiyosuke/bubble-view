# bubble-view
[Android] 吹き出し付きのFrameLayout

![bubble-view](https://user-images.githubusercontent.com/39880172/72416687-9e72f480-37ba-11ea-8a8f-238a702d1b7f.png)

## Usage

吹き出し内に描画したいレイアウトをBubbleViewで囲むだけです
```xml
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">

    <com.kiyosuke.library.BubbleView
        android:id="@+id/bubbleView"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:layout_marginStart="16dp"
        android:layout_marginEnd="16dp"
        android:layout_marginBottom="8dp"
        app:backgroundColor="#FFFFFF"
        app:bubblePadding="8dp"
        app:cornerRadius="8dp"
        app:layout_constraintBottom_toTopOf="@+id/textView"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:strokeColor="#FF0000"
        app:strokeWidth="4dp">

        <TextView
            android:id="@+id/bubbleText"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="吹き出し内のテキストです。\n吹き出し内のテキストです。"
            android:textAlignment="center"
            android:textColor="@android:color/black"
            android:textSize="16sp" />

    </com.kiyosuke.library.BubbleView>

    <TextView
        android:id="@+id/textView"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Bubble View Sample"
        android:textColor="@android:color/black"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintTop_toTopOf="parent" />

</androidx.constraintlayout.widget.ConstraintLayout>
```

### Attributes
* app:backgroundColor  
吹き出し内の背景色

* app:strokeColor  
枠線の色

* app:strokeWidth  
枠線の太さ

* app:cornerRadius  
角丸

* app:pointerWidth  
吹き出しの横幅

* app:pointerHeight  
吹き出しの縦幅

* app:bubblePadding  
吹き出し内部のPadding

* app:pointerAlignment  
吹き出しの描画位置
