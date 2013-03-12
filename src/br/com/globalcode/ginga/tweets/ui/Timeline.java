package br.com.globalcode.ginga.tweets.ui;

import java.awt.Color;
import java.io.IOException;

import br.com.globalcode.ginga.tweets.model.Tweet;

import com.sun.dtv.lwuit.Component;
import com.sun.dtv.lwuit.Container;
import com.sun.dtv.lwuit.Font;
import com.sun.dtv.lwuit.Image;
import com.sun.dtv.lwuit.Label;
import com.sun.dtv.lwuit.TextArea;
import com.sun.dtv.lwuit.geom.Dimension;
import com.sun.dtv.lwuit.layouts.BoxLayout;
import com.sun.dtv.lwuit.layouts.FlowLayout;
import com.sun.dtv.lwuit.plaf.Border;
import com.sun.dtv.lwuit.plaf.Style;

/**
 * Componente de timeline do ginga-tweets.
 * 
 * @author Newton Rhomel Beck Angelini [newton.beck@gmail.com]
 * @author Cesar Augusto Nogueira [cesarnogueira1210@gmail.com]
 * 
 */
public class Timeline extends Container {

	public Timeline() {
		super(new BoxLayout(BoxLayout.Y_AXIS));
		this.getStyle().setBgColor(new Color(192, 222, 237));
		this.getStyle().setBgTransparency(200);
		
		this.getStyle().setPadding(Component.TOP, 20);
		this.getStyle().setPadding(Component.LEFT, 20);
		this.getStyle().setPadding(Component.RIGHT, 40);
		
		Label lblImagemTwitter = new Label();
		
		try {
			lblImagemTwitter.setIcon(Image.createImage("image/topoTwitter.jpg"));
			lblImagemTwitter.setPreferredSize(new Dimension(80, 80));
			lblImagemTwitter.getStyle().setBgTransparency(0);
			this.addComponent(lblImagemTwitter);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Label busca = new Label("Tweets com a hashcode #gingajava");
		Style buscaStyle = busca.getStyle();
		buscaStyle.setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_SMALL));
		buscaStyle.setFgColor(Color.WHITE);
		buscaStyle.setBgTransparency(0);
		
		this.addComponent(busca);
	}

	public void addTweet(Tweet tweet) {
		Label lblImagem = new Label();
		Image imagem = tweet.getImagem();
		
		if(imagem!=null) {
			lblImagem.setIcon(imagem);
//			try {
//				lblImagem.setIcon(Image.createImage("image/noimage.png"));
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
		} else {
			try {
				Image semImagem = Image.createImage("image/noimage.png");
				lblImagem.setIcon(semImagem);
			} catch (IOException e) {
				lblImagem.setText("Sem imagem");
			}
		}
		
		Style imageStyle = lblImagem.getStyle();
		imageStyle.setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_SMALL));
		imageStyle.setFgColor(Color.WHITE);
		imageStyle.setBgTransparency(0);
		
		Label lblUsuario = new Label(tweet.getUsuario());
		lblUsuario.setPreferredSize(new Dimension(152, 40));
		
		Style usuarioStyle = lblUsuario.getStyle();
		usuarioStyle.setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_SMALL));
		usuarioStyle.setFgColor(Color.BLUE);
		usuarioStyle.setBgTransparency(0);
		
		TextArea lblTweet = new TextArea(70, 350);
		lblTweet.setEditable(false);
		lblTweet.getStyle().setBorder(Border.createLineBorder(3));
		lblTweet.setPreferredSize(new Dimension(300, 80));
		lblTweet.setText(tweet.getConteudo());
		lblTweet.setIsScrollVisible(false);
		
		Style messageStyle = lblTweet.getStyle();
		messageStyle.setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_SMALL));
		messageStyle.setFgColor(Color.BLACK);
		messageStyle.setBgTransparency(0);
		messageStyle.setBorder(Border.createEmpty());
		
		lblImagem.setPreferredSize(new Dimension(48,48));
				
		lblImagem.setStyle(imageStyle);
		lblUsuario.setStyle(usuarioStyle);
		lblTweet.setStyle(messageStyle);
		
		Container container = new Container();
		container.setLayout(new FlowLayout());
		
		container.addComponent(lblImagem);
		container.addComponent(lblUsuario);
		container.setSmoothScrolling(true);
		
		Container containerTweet = new Container(new BoxLayout(BoxLayout.Y_AXIS));
		containerTweet.addComponent(container);
		containerTweet.addComponent(lblTweet);
		containerTweet.getStyle().setBgColor(Color.WHITE);
		containerTweet.getStyle().setBgTransparency(200);
		containerTweet.getStyle().setBorder(Border.createRoundBorder(25, 25, Color.WHITE));
		containerTweet.getStyle().setMargin(Component.BOTTOM, 10);
		containerTweet.setPreferredSize(new Dimension(400, 150));

//		Container containerTweetTopo = new Container(new BoxLayout(BoxLayout.Y_AXIS));
//		Label imagemTopo = new Label();
//		try {
//			imagemTopo.setIcon(Image.createImage("image/topoTwitter.jpg"));
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}		
//		containerTweetTopo.addComponent(container);
//		containerTweetTopo.addComponent(lblTweet);
//		containerTweetTopo.getStyle().setBgTransparency(200);
//		containerTweetTopo.getStyle().setMargin(Component.BOTTOM, 10);
//		containerTweetTopo.setPreferredSize(new Dimension(80, 80));		
//		containerTweet.addComponent(containerTweetTopo);
//		
		
		this.addComponent(containerTweet);
	}	

}
