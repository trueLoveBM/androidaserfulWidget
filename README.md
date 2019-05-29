# androidusefulWidget
安卓好用的控件封装
请在你的项目的build.grade文件中添加如下语句，添加依赖

使用方式
1.在你的project的build.gradle中添加如下语句

	allprojects {
		repositories {
			...
			maven { url 'https://www.jitpack.io' }
		}
	}
 2.在你项目的build.gradle文件内添加依赖
 
	dependencies {
	        implementation 'com.github.trueLoveBM:androidusefulWidget:V1.10'
	}

封装的控件包括

1.StepView 步骤控件，实现效果如下,内置了步骤控件适配器StepAdapter，您看根据自己的需求实现自己的Adapter。

![image](https://github.com/trueLoveBM/androidusefulWidget/blob/master/image/stepView.gif)

2.RecycleViewDivider 万能RecycleView分割线，详情请查看类注释



3.WaveViewByBezier WaveViewBySinCos 波浪控件，分别基于贝塞尔曲线和正余弦实现

![image](https://github.com/trueLoveBM/androidusefulWidget/blob/master/image/waveView.gif)

4.SwipeItemLayout RecycleView的右滑菜单，具体效果和qq的侧滑一样

![image](https://github.com/trueLoveBM/androidusefulWidget/blob/master/image/SwipeItemView.gif)

5.CustomDatePicker  日期选择器和时间选择器

![image](https://github.com/trueLoveBM/androidusefulWidget/blob/master/image/datepickerView.gif)

6.PickerView 选择器的使用（日期选择器基于此实现）

![image](https://github.com/trueLoveBM/androidusefulWidget/blob/master/image/pickerView.gif)

7.CheckBoxSample Meterail Design 风格的CheckBox

![image](https://github.com/trueLoveBM/androidusefulWidget/blob/master/image/checkBoxView.gif)

8.NoScollBarGridView  永远不会出现滚动条的GridView

9.MyOneLineView 用于实现通用的我的界面效果

![image](https://github.com/trueLoveBM/androidusefulWidget/blob/master/image/mineLineOneView.gif)

10.和百度UEditor一样的html富文本编辑器

![image](https://github.com/trueLoveBM/androidusefulWidget/blob/master/image/summernote.gif)


  
