package br.com.globalcode.ginga;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;

import javax.microedition.xlet.Xlet;
import javax.microedition.xlet.XletContext;
import javax.microedition.xlet.XletStateChangeException;

import org.json.JSONException;

import br.com.globalcode.ginga.engine.Parser;
import br.com.globalcode.ginga.engine.ParserFactory;
import br.com.globalcode.ginga.model.Conteudo;
import br.com.globalcode.ginga.tweets.model.Tweet;
import br.com.globalcode.ginga.tweets.ui.Timeline;

import com.sun.dtv.lwuit.Button;
import com.sun.dtv.lwuit.Container;
import com.sun.dtv.lwuit.Font;
import com.sun.dtv.lwuit.Form;
import com.sun.dtv.lwuit.Image;
import com.sun.dtv.lwuit.Label;
import com.sun.dtv.lwuit.events.ActionEvent;
import com.sun.dtv.lwuit.events.ActionListener;
import com.sun.dtv.lwuit.geom.Dimension;
import com.sun.dtv.lwuit.layouts.BorderLayout;
import com.sun.dtv.lwuit.layouts.BoxLayout;
import com.sun.dtv.lwuit.plaf.Border;
import com.sun.dtv.lwuit.plaf.Style;
import com.sun.dtv.ui.event.KeyEvent;
import com.sun.dtv.ui.event.RemoteControlEvent;
import com.sun.dtv.ui.event.UserInputEvent;

/**
 * Definição de Layout para o Ginga-tweets.
 * 
 * @author Newton Rhomel Beck Angelini [newton.beck@gmail.com]
 * @author Cesar Augusto Nogueira [cesarnogueira1210@gmail.com]
 * 
 */
public class MainXlet implements Xlet {

	private XletContext context;

	public void destroyXlet(boolean arg0) throws XletStateChangeException {
		context.notifyDestroyed();
	}

	public void initXlet(XletContext arg0) throws XletStateChangeException {
		System.out.println("**** DEBUG -- initXlet");

		this.context = arg0;
	}

	public void pauseXlet() {
	}

	public void startXlet() throws XletStateChangeException {
		System.out.println("**** DEBUG -- startXlet");

		final Form f = new Form();
		f.setLayout(new BorderLayout());
		Style formStyle = f.getStyle();
		formStyle.setBgColor(Color.white);
		formStyle.setBgTransparency(0);

		Label tweetsLogo = new Label();
		Style labelTopStyle = tweetsLogo.getStyle();
		labelTopStyle.setFont(Font.getFont("Arial"));

		final Button btn = new Button("Listar mais tweets");
		Style btnStyle = btn.getStyle();

		btnStyle.setFont(Font.createSystemFont(Font.FACE_SYSTEM,
				Font.STYLE_PLAIN, Font.SIZE_MEDIUM));
		btnStyle.setBgColor(new Color(192, 222, 237));
		btnStyle.setBgTransparency(200);
		btnStyle.setBorder(Border.createEmpty());
		btnStyle.setFgSelectionColor(Color.BLUE);
		btnStyle.setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_SMALL));
		btnStyle.setBgSelectionColor(Color.BLUE);
		
		final Timeline timeLine = new Timeline();

		btn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				new Thread(new Runnable() {
					public void run() {
						buscarTweets(f, btn, timeLine);
						f.repaint();
					}
				}).start();
			}

		});

		Container container = new Container();
		container.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
		
		container.addComponent(timeLine);
		container.addComponent(btn);
	
		f.addComponent(BorderLayout.WEST, container);

		this.buscarTweets(f, btn, timeLine);

		f.show();
	}

	private void setTweets(final Timeline timeLine,
			java.util.List generatedTweets) {
		
		timeLine.removeAll();
		
		for (int i = 0; i < generatedTweets.size(); i++) {
			Conteudo conteudo = (Conteudo) generatedTweets.get(i);
			Tweet newTweet = new Tweet(conteudo.getImagem(),
					conteudo.getNome(), conteudo.getDescricao());
			timeLine.addTweet(newTweet);
		}
	}

	// Captura ações de evento e controle remoto
	public void userInputEventReceived(UserInputEvent inputEvent) {
		KeyEvent event = (KeyEvent) inputEvent;
		if (event.getKeyCode() == RemoteControlEvent.VK_CONFIRM) {
			// TODO
		}
	}

	private void buscarTweets(final Form f, final Button btn,
			final Timeline timeLine) {
		btn.setText("Atualizar tweets");
		System.out.println("**** DEBUG -- readingSearchedTweets");
		java.util.List generatedTweets = new ArrayList();
		Parser parser= ParserFactory.createParser(ParserFactory.PARSER_PROGRAMA);
		try {
			generatedTweets = parser.parse("gingajava");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		System.out.println("**** DEBUG -- finishReadingSearchedTweets");

		setTweets(timeLine, generatedTweets);
	}

}
