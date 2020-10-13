package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import static java.lang.String.*;

public class MainActivity extends AppCompatActivity {


    TextView workspace, test;
    double curNum=0;
    double coef=10;
    String pretag="none";
    String tag="none";
    double preNum=0;
    double res=0;

    int nodiv=1;
    int curfloat=0;
    int prefloat=0;
    boolean notResult=true;
    boolean divByZero=false;

    public void reset(){
        preNum=0;
        curNum=0;
        coef=10;
        pretag="none";
        tag="none";
        res=0;
        nodiv=1;
        curfloat=0;
        prefloat=0;
        notResult=true;
        divByZero=false;
    }
    public void display(){
        if(divByZero){
            workspace.setText("Error");
            reset();
        }else{
            if(nodiv==1 && curfloat<=4){
                if(coef==10 && notResult)
                    workspace.setText(format("%d", (long) curNum));
                else if(curfloat<=1 )
                    workspace.setText(format("%.1f", curNum));
                else if(curfloat==2)
                    workspace.setText(format("%.2f", curNum));
                else if(curfloat==3)
                    workspace.setText(format("%.3f", curNum));
                else if(curfloat==4)
                    workspace.setText(format("%.4f", curNum));
            }else
                workspace.setText(format("%.5f", curNum));
        }
        test.setText(format("%.5f " + " %.5f" + " %.5f"+ " %f",preNum,curNum,res,coef));
    }
    public void inputNumber(int i){
        if(coef==10)
            curNum=curNum*coef+i;
        else if(curfloat<=4){
            curNum+=coef*i;
            coef/=10;
            curfloat+=1;
        }
        notResult=true;
    }
    public void ans(){
        preNum=0;
        pretag="none";
        tag="none";
        res=0;
        nodiv=1;
    }
    public void calculate(){
        if((pretag.equals("none") || pretag.equals("plus")) && (tag.equals("plus") || tag.equals("minus")))
            res+=curNum;
        else if(pretag.equals("minus") && (tag.equals("plus") || tag.equals("minus")))
            res-=curNum;
        else if((pretag.equals("none") || pretag.equals("plus"))  && (tag.equals("mul") || tag.equals("div")))
            preNum=curNum;
        else if(pretag.equals("minus") && (tag.equals("mul") || tag.equals("div")))
            preNum=-1*curNum;
        else if(pretag.equals("mul")){
            preNum*=curNum;
            curfloat=curfloat+prefloat;
            if(tag.equals("plus") || tag.equals("minus"))
                res+=preNum;
        }
        else if(pretag.equals("div")){
            if(curNum!=0)
                preNum/=curNum;
            else{
                divByZero=true;
            }
            nodiv=0;
            if(tag.equals("plus") || tag.equals("minus"))
                res+=preNum;
        }
        curNum=0;
        coef=10;
        pretag=tag;
        prefloat=curfloat;
        curfloat=0;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        workspace = (TextView) findViewById(R.id.work_space);
        test = (TextView) findViewById(R.id.test);

        findViewById(R.id.zero).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               inputNumber(0);
               display();
            }
        });
        findViewById(R.id.one).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputNumber(1);
                display();
            }
        });
        findViewById(R.id.two).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputNumber(2);
                display();
            }
        });
        findViewById(R.id.three).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputNumber(3);
                display();
            }
        });
        findViewById(R.id.four).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputNumber(4);
                display();
            }
        });
        findViewById(R.id.five).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputNumber(5);
                display();
            }
        });
        findViewById(R.id.six).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputNumber(6);
                display();
            }
        });
        findViewById(R.id.seven).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputNumber(7);
                display();
            }
        });
        findViewById(R.id.eight).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputNumber(8);
                display();
            }
        });
        findViewById(R.id.nine).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputNumber(9);
                display();
            }
        });
        findViewById(R.id.point).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(coef==10){
                    coef= 0.1;
                }
                display();
            }
        });
        findViewById(R.id.CE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                curNum=0;
                coef=10;
                nodiv=1;
                curfloat=0;
                display();
            }
        });
        findViewById(R.id.C).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reset();
                display();
            }
        });
        findViewById(R.id.BS).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(curNum!=0){
                    if(coef>=0.1){
                        coef=10;
                        curNum= (long) curNum/10;
                    }
                    else{
                        curNum=((long) ((curNum/(coef*10))/10))*coef*100;
                        coef*=10;
                        curfloat-=1;
                    }
                }
                if(!notResult) reset();
                display();
            }
        });
        findViewById(R.id.negative).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                curNum=-1*curNum;
                display();
            }
        });
        findViewById(R.id.div).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tag="div";
                calculate();
                display();
            }
        });
        findViewById(R.id.multiply).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tag="mul";
                calculate();
                display();
            }
        });
        findViewById(R.id.plus).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tag="plus";
                calculate();
                display();
            }
        });
        findViewById(R.id.minus).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tag="minus";
                calculate();
                display();
            }
        });
        findViewById(R.id.enter).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tag="plus";
                calculate();
                curNum=res;
                curfloat=prefloat;
                notResult=false;
                display();
                ans();
            }
        });
    }
}