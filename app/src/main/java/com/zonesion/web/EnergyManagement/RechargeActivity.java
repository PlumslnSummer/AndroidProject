package com.zonesion.web.EnergyManagement;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.zonesion.web.EnergyManagement.SQLite.MonthSQLiteHelper;

/**
 * 代码为原创
 */
public class RechargeActivity extends AppCompatActivity {

    private TextView tv_monthquantity,tv_remain_amount;
    private EditText et_recharge;
    private Button btn_recharge;
    private String monthquantity,money_recharge,remain;
    private Float amount,total_amount=0.0f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recharge);

        initView();
    }

    private void initView() {
        Intent intent=getIntent();
        monthquantity=intent.getStringExtra("monthquantity");
        tv_monthquantity=findViewById(R.id.tv_month_quantity);
        et_recharge=findViewById(R.id.et_recharge);
        btn_recharge=findViewById(R.id.btn_recharge);
        tv_remain_amount=findViewById(R.id.tv_remain_amount);

        tv_monthquantity.setText(monthquantity);
        //将获取数据库中每次的剩余金额相加
        SQLiteOpenHelper helper= MonthSQLiteHelper.getmInstance(RechargeActivity.this);
        SQLiteDatabase db_remain=helper.getReadableDatabase();
        if(db_remain.isOpen()){
            String sql_remain="select * from month;";
            Cursor cursor = db_remain.rawQuery(sql_remain, null);
            if(cursor.getCount()==0){
                Toast.makeText(RechargeActivity.this,"没有充值过金额",Toast.LENGTH_SHORT).show();
            }else {
                cursor.moveToFirst();
                //将数据库中的剩余金额赋值给amount1
                String amount1=cursor.getString(cursor.getColumnIndex("remainamount"));
                total_amount=total_amount+Float.parseFloat(amount1);
            }
            while (cursor.moveToNext()){
                String amount1=cursor.getString(cursor.getColumnIndex("remainamount"));
                total_amount=total_amount+Float.parseFloat(amount1);
            }
            tv_remain_amount.setText(String.valueOf(total_amount));
            cursor.close();
        }
        db_remain.close();
        btn_recharge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                money_recharge=et_recharge.getText().toString().trim();


                if(TextUtils.isEmpty(money_recharge)){
                    Toast.makeText(RechargeActivity.this,"请输入充值金额",Toast.LENGTH_SHORT).show();
                }else{
                    amount=Float.parseFloat(money_recharge)-Float.parseFloat(monthquantity)*1;
                    remain=String.valueOf(amount).toString();
                    SQLiteOpenHelper helper= MonthSQLiteHelper.getmInstance(RechargeActivity.this);
                    SQLiteDatabase db_month=helper.getWritableDatabase();
                    if(db_month.isOpen()){
                        String sql_month="insert into month(quantity,money,remainamount) values(?,?,?);";
                        db_month.execSQL(sql_month,new Object[]{monthquantity,money_recharge,remain});
                        Toast.makeText(RechargeActivity.this,"充值成功"+money_recharge+"元",Toast.LENGTH_SHORT).show();
                    }
                    db_month.close();
                    total_amount=total_amount+amount;
                    tv_remain_amount.setText(String.valueOf(total_amount));
                }

            }
        });
    }

}