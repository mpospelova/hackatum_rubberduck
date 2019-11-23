package ByteTheDust.Rubberdocs;

import java.util.Scanner;
import java.util.concurrent.ExecutionException;

import com.microsoft.cognitiveservices.speech.*;

/**
 * Quickstart: recognize speech using the Speech SDK for Java.
 */
public class SpeechToText {
	//This class implements the Azure API in an object oriented way,
	//adds functionality to "record until manually stopped"

    // Replace below with your own subscription key
	private static String speechSubscriptionKey = "a5289b47e09c4e1887d5b7c1283d561b";
    // Replace below with your own service region (e.g., "westus").
	private static String serviceRegion = "westus";

	private StringBuffer recognizedText;

	private SpeechRecognizer recognizer;

	//To update the text view live
	private final INewTranslatedtext newTranslatedtext;

	public SpeechToText(final INewTranslatedtext newTranslatedtext) {
	    this.newTranslatedtext=newTranslatedtext;
	    this.recognizedText = new StringBuffer();

        SpeechConfig config = SpeechConfig.fromSubscription(speechSubscriptionKey, serviceRegion);
        assert(config != null);

        recognizer = new SpeechRecognizer(config);
        assert(recognizer != null);


        // Subscribes to events.
        recognizer.recognizing.addEventListener((s, e) -> {
            System.out.println("RECOGNIZING: Text=" + e.getResult().getText());
        });

        recognizer.recognized.addEventListener((s, e) -> {
            if (e.getResult().getReason() == ResultReason.RecognizedSpeech) {
                final String text=e.getResult().getText();
                System.out.println("RECOGNIZED: Text=" + text);
                recognizedText.append(text);
                if(newTranslatedtext!=null){
                    newTranslatedtext.onNewTranslatedText(text);
                }
            }
            else if (e.getResult().getReason() == ResultReason.NoMatch) {
                System.out.println("NOMATCH: Speech could not be recognized.");
            }
        });

        recognizer.canceled.addEventListener((s, e) -> {
            System.out.println("CANCELED: Reason=" + e.getReason());

            if (e.getReason() == CancellationReason.Error) {
                System.out.println("CANCELED: ErrorCode=" + e.getErrorCode());
                System.out.println("CANCELED: ErrorDetails=" + e.getErrorDetails());
                System.out.println("CANCELED: Did you update the subscription info?");
            }
        });

        recognizer.sessionStarted.addEventListener((s, e) -> {
            System.out.println("\n    Session started event.");
        });

        recognizer.sessionStopped.addEventListener((s, e) -> {
            System.out.println("\n    Session stopped event.");
        });
	}

	public void startRecognizer(){
	    this.recognizedText = new StringBuffer();

        // Starts continuous recognition. Uses stopContinuousRecognitionAsync() to stop recognition.
        System.out.println("starting recognition...");
        try {
            recognizer.startContinuousRecognitionAsync().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public String stopRecognizer(){
        try {
            recognizer.stopContinuousRecognitionAsync().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return recognizedText.toString();
    }

    public String getRecognizedText(){
	    return this.recognizedText.toString();
    }

    /**
     * @param args Arguments are ignored in this sample.
     */
	//
    public static void main(String[] args) {
        SpeechToText speechToText = new SpeechToText(null);

        speechToText.startRecognizer();

        System.out.println("Press any key to stop");
        new Scanner(System.in).nextLine();

        speechToText.stopRecognizer();
        System.out.println("Total text: " + speechToText.getRecognizedText());
    }
}
