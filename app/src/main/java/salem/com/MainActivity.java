package salem.com;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int color=0;

    int[] state ={2,2,2,2,2,2,2,2,2};   // 0 is red and 1 is yellow and 2 is not uesd

    int[][] winningCases={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    boolean gameStatus = true;
    int redScore=0;
    int yellowScore=0;

    public void dropIn (View v)
    {
        ImageView pic=(ImageView) v;
        //pic.setTranslationY(-1000f);     will pu this line below so when we click again the color doesn't disappear

        int tagNumber =Integer.parseInt(pic.getTag().toString());  // getting the "tag" value (index) of the position

        if(state[tagNumber]==2 && gameStatus) {

            if (color == 0) {
                pic.setTranslationY(-1000f);
                pic.setImageResource(R.drawable.red);
                pic.animate().translationYBy(1000f).rotation(180f).setDuration(300);
                state[tagNumber]=color;
                color = 1;
            } else {
                pic.setTranslationY(-1000f);
                pic.setImageResource(R.drawable.yellow);
                pic.animate().translationYBy(1000f).rotation(180f).setDuration(300);
                state[tagNumber]=color;
                color = 0;
            }
        } // no need for else


        for(int [] winnigCase: winningCases)
        {
            String winner="Red";

            if(state[winnigCase[0]]==state[winnigCase[1]] && state[winnigCase[1]]==state[winnigCase[2]] && state[winnigCase[0]]!=2 )
            {
                if(state[winnigCase[0]]==1 )
                {
                    winner = "yellow";
                    TextView msg= findViewById(R.id.textView);
                    msg.setText( winner+ " WON!!");
                    LinearLayout layout = findViewById(R.id.linearLayout);
                    layout.setBackgroundColor(Color.rgb(255,251,0));
                    layout.setVisibility(View.VISIBLE);
                    gameStatus=false;
                    yellowScore++;
                    TextView yellowWins = findViewById(R.id.textView3);
                    yellowWins.setText(String.valueOf(yellowScore));
                    break;
                }
                else
                {
                    TextView msg = findViewById(R.id.textView);
                    msg.setText(winner + " WON!!");
                    LinearLayout layout = findViewById(R.id.linearLayout);
                    layout.setBackgroundColor(Color.rgb(255,0,0));
                    layout.setVisibility(View.VISIBLE);
                    gameStatus = false;
                    TextView redWins = findViewById(R.id.textView2);
                    redScore++;
                    redWins.setText(String.valueOf(redScore));
                    break;

                }

            }
            else
            {
                boolean gamesOver=true;

                for(int i:state)
                {
                    if (i==2)
                    {
                        gamesOver=false;
                    }
                }
                if (gamesOver)
                {
                    TextView msg = findViewById(R.id.textView);
                    msg.setText("It's a tie !");
                    LinearLayout layout = findViewById(R.id.linearLayout);
                    layout.setBackgroundColor(Color.rgb(151,151,151));
                    layout.setVisibility(View.VISIBLE);
                    gameStatus = false;
                }

            }

        }



    }

    public void playAgain(View v)
    {
        color=0;
        LinearLayout linearLayout = findViewById(R.id.linearLayout);
        linearLayout.setVisibility(View.INVISIBLE);
        for(int i=0;i<state.length;i++)   // resetting game state
        {
            state[i]=2;
        }

        gameStatus=true;

        GridLayout gridLayout1;
        gridLayout1 = findViewById(R.id.gridLayout3);

        for(int i=0;i<gridLayout1.getChildCount();i++)
        {
            ((ImageView)gridLayout1.getChildAt(i)).setImageResource(0);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
