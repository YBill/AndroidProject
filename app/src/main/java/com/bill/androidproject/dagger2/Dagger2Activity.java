package com.bill.androidproject.dagger2;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bill.androidproject.R;

import javax.inject.Inject;

/**
 * author : Bill
 * date : 2021/5/31
 * description :
 */

/**
 * 使用注解创建Student，如下两种使用Dagger2方式
 *
 * 1、Student构造函数添加@Inject
 *    StudentModule类前添加@Module
 *    StudentComponent类前添加@Component(modules = StudentModule.class)
 *    使用前初始化：DaggerStudentComponent.builder().build().inject(this); @Inject Student student;
 *
 * 2、StudentModule类前添加@Module；在此类中创建方法返回Student对象，并在方法前加@Provides
 *    StudentComponent类前添加@Component(modules = StudentModule.class)
 *    使用前初始化：DaggerStudentComponent.builder().studentModule(new StudentModule(this)).build().inject(this); @Inject Student student;
 */
public class Dagger2Activity extends AppCompatActivity {

    @Inject
    Student student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dagger2);

        DaggerStudentComponent.builder()
                .studentModule(new StudentModule(this))
                .build()
                .inject(this);

    }

    public void handleClick(View view) {
        Toast.makeText(getApplicationContext(), student.toString(), Toast.LENGTH_SHORT).show();
    }
}
