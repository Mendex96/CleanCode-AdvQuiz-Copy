package es.ulpgc.eite.da.quiz.question;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import es.ulpgc.eite.da.quiz.R;

public class QuestionActivity
    extends AppCompatActivity implements QuestionContract.View {

  public static String TAG = QuestionActivity.class.getSimpleName();

  private QuestionContract.Presenter presenter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_question);

    ((TextView) findViewById(R.id.nextButton)).setText(R.string.next_button);
    ((TextView) findViewById(R.id.cheatButton)).setText(R.string.cheat_button);

    // do the setup
    QuestionScreen.configure(this);

    if(savedInstanceState == null) {
      presenter.onStart();

    } else {
      presenter.onRestart();
    }
  }

  @Override
  protected void onResume() {
    super.onResume();

    // load the data
    presenter.onResume();
  }

  @Override
  public void displayQuestion(QuestionViewModel viewModel) {
    //Log.e(TAG, "displayQuestion()");

    // deal with the data
    ((TextView) findViewById(R.id.qestionTextView)).setText(viewModel.question);
    ((TextView) findViewById(R.id.option1Button)).setText(viewModel.option1);
    ((TextView) findViewById(R.id.option2Button)).setText(viewModel.option2);
    ((TextView) findViewById(R.id.option3Button)).setText(viewModel.option3);
    //((TextView) findViewById(R.id.replyTextView)).setText(viewModel.reply);

    findViewById(R.id.option1Button).setEnabled(viewModel.optionEnabled);
    findViewById(R.id.option2Button).setEnabled(viewModel.optionEnabled);
    findViewById(R.id.option3Button).setEnabled(viewModel.optionEnabled);
    findViewById(R.id.nextButton).setEnabled(viewModel.nextEnabled);
    findViewById(R.id.cheatButton).setEnabled(viewModel.cheatEnabled);
  }

  @Override
  public void resetReply() {
    ((TextView) findViewById(R.id.replyTextView))
        .setText(R.string.empty_reply);
  }

  @Override
  public void updateReply(boolean isCorrect) {
    if(isCorrect){
      ((TextView) findViewById(R.id.replyTextView))
          .setText(R.string.correct_reply);
    } else {
      ((TextView) findViewById(R.id.replyTextView))
          .setText(R.string.incorrect_reply);
    }
  }


  public void onNextButtonClicked(View view) {

  }

  public void onCheatButtonClicked(View view) {

  }

  public void onOptionButtonClicked(View view) {
    //Log.e(TAG, "onOptionButtonClicked()");

    int option = Integer.valueOf((String) view.getTag());
    //Log.e(TAG, "option: "+option);
    presenter.onOptionButtonClicked(option);
  }


  @Override
  public void injectPresenter(QuestionContract.Presenter presenter) {
    this.presenter = presenter;
  }
}
