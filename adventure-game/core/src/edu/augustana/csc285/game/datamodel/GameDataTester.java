package edu.augustana.csc285.game.datamodel;

public class GameDataTester {

	public static void main(String[] args) {
		GameData gd = new GameData();
		
		Slide s0 = new Slide("GameData/SlideImages/slide0.jpg",
				"You’re a young Swedish immigrant to America in 1880. You’ve made the tough decision to leave your family and life in Sweden behind. Will you survive and prosper in America?\n",
				null, null, 0);
		s0.addOption(new Option("Take the Journey", "You have embarked on a journey!", null, 1, null, null));
		Slide s1 = new Slide("GameData/SlideImages/slide1.jpg",
				"It’s the 1880s in Sweden. The opportunities in America, such as cheap land and a stable economy, make migration attractive to many Europeans who face problems related to overpopulation. Millions have migrated from their homelands for a new life. You now wish to join them. \nWho are you?",
				null, null, 1);
		Option temp = new Option("Anders Bengtsson ", "You choose Anders Bengtsson", null, 2, null, null);
		// temp.addEffect(Effect);
		s1.addOption(temp);
		temp = new Option("Lovisa Eriksdotter", "You choose Lovisa Eriksdotter", null, 3, null, null);
		// temp.addEffect(Effect);
		s1.addOption(temp);

		Slide s2 = new Slide("GameData/SlideImages/slide2.jpg",
				"You are an 18 year-old male from Mellby Parish, Småland. You are the youngest of 4 brothers and will not have any farmland to work. There are too many men in your family and not enough farmland. Additionally, the crop failures of 1867-1868 hit your family farm hard. You would also like to avoid Swedish military conscription. You are coming to America to own your own farmland or find a good job. ",
				null, null, 2);
		s2.addOption(new Option("Continue", null, null, 4, null, null));

		Slide s3 = new Slide("GameData/SlideImages/slide3.jpg",
				"You are an 18 year-old female maid from Stockholm. You dislike your job as maid and can’t seem to find a better one because there is too much competition (population has been booming in the city and countryside). You decide to seek better employment in America.",
				null, null, 3);
		s3.addOption(new Option("Continue", null, null, 4, null, null));

		Slide s4 = new Slide("GameData/SlideImages/slide6.jpg",
				"Receiving letters from those who journeyed to America prompted many of your family friends to pursue their own journey over. Life must be treating them well in America— economic opportunities, building their own legacy and family. The American Dream is a strong pull for Swedes, yourself included. \n"
						+ "\n" + "Choose a letter to read:",
				null, null, 4);
		s4.addOption(new Option("John Deere Letter", null, null, 5, null, null));
		s4.addOption(new Option("Illinois Letter", null, null, 6, null, null));
		s4.addOption(new Option("Washington Letter", null, null, 7, null, null));
		s4.addOption(new Option("Minnesota Letter", null, null, 8, null, null));

		Slide s5 = new Slide("GameData/SlideImages/slide5.jpg", "To Whom It May Concern,\n"
				+ "We hope you find this letter in good spirits. On behalf of the John Deere Company, we thank you for meeting with us concerning about jobs within our factories. We are pleased to inform you you have been recruited for a position at the Moline factory site. We are looking forward to seeing you again and have safe travels. Please bring along this notarized letter with you to our recruitment office.\n"
				+ "God speed,\n" + "From the Office of John Deere, President of John Deere Co.", null, null, 5);
		s5.addOption(new Option("Continue", null, null, 9, null, null));

		Slide s6 = new Slide("GameData/SlideImages/slide6.jpg", "Dear Cousin,\n"
				+ "May God find you well, dear friend! God is good and God is plentiful. He has shown me the way to a brighter future here in America. My journey has led myself and my travelling companions to a quaint town in the state of Illinois called Moline. It may be quaint now but we are fortunate to have factories and farming jobs available for the people. I have friends all over Illinois including Rockford and Chicago! We stay in touch through correspondence and the occasional social visit when we can. My piece of advice for you, should you make the journey, is to find peace with yourself and our loving God. For He is the way and will guide you! The Christian faith has given me hope and salvation along my journey. My fellow companions and myself attend the Lutheran service held by a pastor who is also from Sweden. It is wonderful to have familiarity in a strange place. Services are something to look forward to as it brings us closer to our Maker and builds a strong sense of community. I pray for all the success in the world for you as you make your own journey. \n"
				+ "Lovingly,\n" + "Erika Leiffson", null, null, 6);
		s6.addOption(new Option("Continue", null, null, 9, null, null));

		Slide s7 = new Slide("GameData/SlideImages/slide7.jpg", "Dear Cousin,\n" + 
				"I have been blessed to have found a place I can call home. My journeys were long, and at times I had felt grief for leaving my beautiful Sweden. I remember so clearly how it felt to be a stranger in a strange place. Only having a small trunk and broken English left me apprehensive of my surroundings. I am thankful that I have experienced no ill wills on my passage to America. I had heard some of my kin had travelled to the far west of this vast country, in a place called Washington state. My family settled near Tacoma and are now prosperous farmers and fishermen! I decided to try my hand for this place. I took the Walla Walla railroad instead of the Northern Pacific, as it was the cheaper option. I was taken to Seattle and found a boardinghouse for Swedes. Within a month I had found employment as a maid to one of the founding families. They treat me well despite some language barrier, but I am becoming more proficient and feel more confident in my new home.\n" + 
				"I do wish you well and hope someday to meet you again in my new home! Give my love to my family and friends!\n" + 
				"\n" + 
				"Linnea Parsson\n", null, null, 7);
		s7.addOption(new Option("Continue", null, null, 9, null, null));
		
		Slide s8 = new Slide("GameData/SlideImages/slide8.jpg", "Dear Cousin,\n" + 
				"I am content to report that I have found steady employment as my own master. I had arrived only two months in St. Paul, working odds and ends in the town when I noticed an advertisement of purchasable land not far from my lodgings. Land is so cheap here in America! I saved enough to buy my own lot and have begun to till and grind the land. My hope is to grow peas and potatoes and sell them at market. Let us pray for a good harvest this next season!\n" + 
				"Life has treated me well. The journey is hard on everyone, harder for others. My companion on the trip to America arrived well but I have received word that he has fallen into poverty. He was swindled by a false recruiter at the docks. He is struggling to find employment in the city but, God willing, I hope he will find something soon!\n" + 
				"As you make the journey, make sure to bring plenty of food. They treat you well on the ships and trains but the journey alone can make one homesick. A little piece of home can appease the qualms of the stomach and might make you a friend or two!\n" + 
				"Godspeed dear cousin! \n" + 
				"Arvid Lothbrok\n", null, null, 8);
		s8.addOption(new Option("Continue", null, null, 9, null, null));
		
		Slide s9 = new Slide("GameData/SlideImages/slide9.jpg",
				"To start your journey, you need a few provisions. The first and foremost being your papers. You have $70 USD worth of Kronor (Swedish money)  right now. Do you:\n",
				null, null, 9);
		temp = new Option("Buy official papers from your home parish $20 or 80 SEK", null, null, 10, null, null);
		// temp.addEffect(effect);
		s9.addOption(temp);
		temp = new Option("Buy forged papers $10 or 40 SEK", null, null, 10, null, null);
		// temp.addEffect(effect);
		s9.addOption(temp);

		gd.addSlide(s0);
		gd.addSlide(s1);
		gd.addSlide(s2);
		gd.addSlide(s3);
		gd.addSlide(s4);
		gd.addSlide(s5);
		gd.addSlide(s6);
		gd.addSlide(s7);
		gd.addSlide(s8);
		gd.addSlide(s9);

		String serializedJSONText = gd.toJSON();

		System.out.println(serializedJSONText);

		// GameData gdRecreated = GameData.fromJSON(serializedJSONText);
		// System.out.println("Slide 0 has this image: ");
		// System.out.println(gdRecreated.getSlide(0).getImageFileName());

	}

}