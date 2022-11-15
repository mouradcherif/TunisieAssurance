package com.cherif.tunisieassurance;
import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import com.github.appintro.AppIntro;
import com.github.appintro.AppIntroFragment;
import com.github.appintro.AppIntroPageTransformerType;

public class IntroActivity extends AppIntro {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addSlide(AppIntroFragment.createInstance("Welcome!","This is an Insurance Agency Portal for Agents for Creating and modifying contracts for clients.",R.drawable.namelogo,R.color.indigo_dye_blue));
        addSlide(AppIntroFragment.createInstance("Project Done by ...","Mourad Amine Cherif - GLSI 3",R.drawable.uclogo,R.color.purple_200));
        addSlide(AppIntroFragment.createInstance("Thank you","Thank you for downloading the App !",R.drawable.logo,R.color.turquoise));

        setTransformer(new AppIntroPageTransformerType.Parallax(1.0,-1.0,2.0));
        showStatusBar(false);
        setColorTransitionsEnabled(true);
        setWizardMode(true);
    }

    @Override
    protected void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        Intent intent = new  Intent(getBaseContext(), MenuActivity.class);
        startActivity(intent);
    }



}
