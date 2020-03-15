package es.ulpgc.eite.da.quiz.question;

import androidx.fragment.app.FragmentActivity;

import java.lang.ref.WeakReference;

import es.ulpgc.eite.da.quiz.AppMediator;
import es.ulpgc.eite.da.quiz.R;

public class QuestionScreen {

  public static void configure(QuestionContract.View view) {

    WeakReference<FragmentActivity> context =
        new WeakReference<>((FragmentActivity) view);

    AppMediator mediator = (AppMediator) context.get().getApplication();
    QuestionState state = mediator.getQuestionState();

    String[] quiz = context.get()
        .getResources().getStringArray(R.array.quiz_array);
    //String empty= context.get().getString(R.string.empty_reply);
    //String correct= context.get().getString(R.string.correct_reply);
    //String incorrect= context.get().getString(R.string.incorrect_reply);

    QuestionContract.Router router = new QuestionRouter(mediator);
    QuestionContract.Presenter presenter = new QuestionPresenter(state);
    QuestionContract.Model model = new QuestionModel(quiz);

    /*
    QuestionContract.Model model =
        new QuestionModel(quiz, empty, correct, incorrect);
    */

    presenter.injectModel(model);
    presenter.injectRouter(router);
    presenter.injectView(new WeakReference<>(view));

    view.injectPresenter(presenter);

  }
}
