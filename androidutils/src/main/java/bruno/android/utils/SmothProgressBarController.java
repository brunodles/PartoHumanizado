package bruno.android.utils;

import android.animation.ObjectAnimator;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.ProgressBar;

/**
 * Created by dev on 26/09/2014.
 */
public class SmothProgressBarController {

    public static final int DEFAULT_DIVISIONS = 500;

    private int animationDuration;
    private int divisions;
    private ProgressBar progressBar;
    private Interpolator interpolator;
    private double pct;
    private ProcessAnimator primaryProgress;
    private ProcessAnimator secondaryProgress;

    private SmothProgressBarController() {
    }

    public void setMax(int max) {
        progressBar.setMax(max * divisions);
    }

    public int getMax() {
        return progressBar.getMax() / divisions;
    }

    public void setProgress(float progress) {
        progress = progress * divisions;
        primaryProgress.setProgress((int)progress);
    }

    public void setSecondaryProgress(float progress) {
        progress = progress * divisions;
        secondaryProgress.setProgress((int)progress);
    }

    public int getProgress() {
        return progressBar.getProgress() / divisions;
    }
    public int getSecondaryProgress() {
        return progressBar.getSecondaryProgress() / divisions;
    }

    public double getPrimaryPercentage() {
        int max = getMax();
        if (max == 0)
            return 0;
        return getProgress() * 100 / max;
    }
    public double getSecondaryPercentage() {
        int max = getMax();
        if (max == 0)
            return 0;
        return getSecondaryProgress() * 100 / max;
    }

    private static class ProcessAnimator {

        private static final String PRIMARY_PROGRESS = "progress";
        private static final String SECONDARY_PROGRESS = "secondaryProgress";

        private String propertieName = PRIMARY_PROGRESS;
        private ObjectAnimator animator;
        private SmothProgressBarController smothProgressBarController;

        private ProcessAnimator(String propertieName, SmothProgressBarController smothProgressBarController) {
            this.propertieName = propertieName;
            this.smothProgressBarController = smothProgressBarController;
        }

        void setProgress(int progress) {
            if (animator != null)
                animator.cancel();
//                animator.end();
            animator = ObjectAnimator.ofInt(smothProgressBarController.progressBar, propertieName, progress);
            animator.setDuration(smothProgressBarController.animationDuration);
            animator.setInterpolator(smothProgressBarController.interpolator);
            animator.start();
        }
    }

    public static final class Builder {
        private int divisions = DEFAULT_DIVISIONS;
        private int animationDuration = 500;
        private ProgressBar progressBar;
        private Interpolator interpolator;

        public Builder(ProgressBar progressBar) {
            this.progressBar = progressBar;
        }

        public Builder divisions(int divisions) {
            this.divisions = divisions;
            return this;
        }

        public Builder interpolator(Interpolator interpolator) {
            this.interpolator = interpolator;
            return this;
        }

        public Builder animationDuration(int animationDuration){
            this.animationDuration = animationDuration;
            return this;
        }

        public SmothProgressBarController build() {
            SmothProgressBarController control = new SmothProgressBarController();
            control.progressBar = progressBar;
            control.animationDuration = animationDuration;
            control.divisions = divisions;

            if (interpolator != null)
                control.interpolator = interpolator;
            else
                control.interpolator = new AccelerateDecelerateInterpolator();

            control.primaryProgress = new ProcessAnimator(ProcessAnimator.PRIMARY_PROGRESS, control);
            control.secondaryProgress = new ProcessAnimator(ProcessAnimator.SECONDARY_PROGRESS, control);

            return control;
        }
    }
}
