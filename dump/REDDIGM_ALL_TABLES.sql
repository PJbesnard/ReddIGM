INSERT INTO PUBLIC.REDDIGM_AUTHORITY (NAME) VALUES ('ADMIN');
INSERT INTO PUBLIC.REDDIGM_AUTHORITY (NAME) VALUES ('USER');

INSERT INTO PUBLIC.USERS (ID, DESCRIPTION, EMAIL, NEWSLETTER_SUBSCRIBER, PASSWORD, PICTURE, USERNAME) VALUES (33, null, 'wesh@test.com', false, '$2a$10$xK9uEDUp53/UPawVt9Gvu.CBxFKP43mJ5R6G9LLmf4iZachdgX3ZS', 'https://64.media.tumblr.com/94638a78ebb2f474b5539aee168af44d/tumblr_mxy2k79HUl1smqdb1o1_400.gifv', 'SaladeTomateOignon');
INSERT INTO PUBLIC.USERS (ID, DESCRIPTION, EMAIL, NEWSLETTER_SUBSCRIBER, PASSWORD, PICTURE, USERNAME) VALUES (97, 'Je suis le fameux Louis Binaire, le seul, l''unique, celui qui calcule plus vite que son ombre', 'louisbinaire@gmail.com', false, '$2a$10$eW3bKXZ76PsYYbhWTkhQ6eNFgItLuFPK30Lr1cLA7nAvFRgHkxsLa', 'https://www.gifimili.com/gif/2018/02/bob-l-eponge-arc-en-ciel.gif', 'LouisBinaire');
INSERT INTO PUBLIC.USERS (ID, DESCRIPTION, EMAIL, NEWSLETTER_SUBSCRIBER, PASSWORD, PICTURE, USERNAME) VALUES (162, null, 'bg77@uge.fr', false, '$2a$10$XrQ26WI918hMoO5PET.xSO04bN14/H6HuSDfX7go42o8y2jMT7FsS', null, 'Hoomar');
INSERT INTO PUBLIC.USERS (ID, DESCRIPTION, EMAIL, NEWSLETTER_SUBSCRIBER, PASSWORD, PICTURE, USERNAME) VALUES (205, 'Oh si.... c''est rigolo ....', 'pedrojuan@gmail.com', false, '$2a$10$l2WZzWGZGs1DAWbaJJDoA./VuI8NDdLKrSgDBVZbXLWe4WCXEZJwa', 'https://risibank.fr/cache/stickers/d2254/225426-full.png', 'PedroJuan');
INSERT INTO PUBLIC.USERS (ID, DESCRIPTION, EMAIL, NEWSLETTER_SUBSCRIBER, PASSWORD, PICTURE, USERNAME) VALUES (213, 'A Dinosaur who deserve a good mark for this project and love his teacher from the bottom of his heart', 'ElTiernausore@gmail.com', false, '$2a$10$7qjD0UTzUZUur3U1zO8XQuDA6J9g6l68csMHyfGcEHlfL.A634TkW', 'https://www.leparisien.fr/resizer/e_ONnK23oLDMJT3CLusiA2W3dB8=/932x582/arc-anglerfish-eu-central-1-prod-leparisien.s3.amazonaws.com/public/WS2SPBO64DI5XPEQL5ADGIVTZA.jpg', 'ElTiernausore');
INSERT INTO PUBLIC.USERS (ID, DESCRIPTION, EMAIL, NEWSLETTER_SUBSCRIBER, PASSWORD, PICTURE, USERNAME) VALUES (233, 'Best Java developper you ever seen', 'DarkSasuke77@gmail.com', false, '$2a$10$xImW7rIU3r.AcnnyMc3aG.NPNYpx/HQuWOLff1EBKqm.v4gFO/k4q', 'https://avatars.githubusercontent.com/u/828220?s=400&v=4', 'RemiForax');
INSERT INTO PUBLIC.USERS (ID, DESCRIPTION, EMAIL, NEWSLETTER_SUBSCRIBER, PASSWORD, PICTURE, USERNAME) VALUES (235, 'Mon language prefere et que je maitrise le plus: HTML
Un peu le CSS aussi mais c''est dur ', 'DevPasTerrible@gmail.com', false, '$2a$10$t/d6ShAEWY6XBpxovZVbq.4tW30Ulwdckgz5.7xyrj7p9BP6m.bwm', 'https://media.tenor.com/images/0c95b6e7c5d7fa56ea7b84f8b981d147/tenor.gif', 'DevPasTerrible');
INSERT INTO PUBLIC.USERS (ID, DESCRIPTION, EMAIL, NEWSLETTER_SUBSCRIBER, PASSWORD, PICTURE, USERNAME) VALUES (283, null, 'tototaka@gmail.com', false, '$2a$10$EfRLQnT9Ymv6pq6mct8fce3fH/CnH6aIn1J/EjPw/eVM59zvkSi1e', 'https://static1.purebreak.com/articles/8/87/71/8/@/397790-un-chien-qui-trolle-avec-ses-faux-624x0-1.jpg', 'tototaka');
INSERT INTO PUBLIC.USERS (ID, DESCRIPTION, EMAIL, NEWSLETTER_SUBSCRIBER, PASSWORD, PICTURE, USERNAME) VALUES (284, null, 'admin@reddigm.fr', false, '$2a$10$.JUY/yYY5eb6oSBFUMDCZONthl/1F7watZbMo7RTnKrOuWN0UmW3e', '', 'admin');
INSERT INTO PUBLIC.USERS (ID, DESCRIPTION, EMAIL, NEWSLETTER_SUBSCRIBER, PASSWORD, PICTURE, USERNAME) VALUES (348, null, 'toto@gmail.com', false, '$2a$10$B1rzC5vEyVq0/sDT1OXAQOw8.ZpuUP9z3MIcNAoHOx/0RgimDL/pq', 'https://pbs.twimg.com/media/EeU1cJzX0AAHUUO.jpg', 'Toto');

INSERT INTO PUBLIC.USERS_AUTHORITIES (USERS_ID, AUTHORITIES_NAME) VALUES (33, 'USER');
INSERT INTO PUBLIC.USERS_AUTHORITIES (USERS_ID, AUTHORITIES_NAME) VALUES (97, 'USER');
INSERT INTO PUBLIC.USERS_AUTHORITIES (USERS_ID, AUTHORITIES_NAME) VALUES (162, 'USER');
INSERT INTO PUBLIC.USERS_AUTHORITIES (USERS_ID, AUTHORITIES_NAME) VALUES (205, 'USER');
INSERT INTO PUBLIC.USERS_AUTHORITIES (USERS_ID, AUTHORITIES_NAME) VALUES (213, 'USER');
INSERT INTO PUBLIC.USERS_AUTHORITIES (USERS_ID, AUTHORITIES_NAME) VALUES (233, 'USER');
INSERT INTO PUBLIC.USERS_AUTHORITIES (USERS_ID, AUTHORITIES_NAME) VALUES (235, 'USER');
INSERT INTO PUBLIC.USERS_AUTHORITIES (USERS_ID, AUTHORITIES_NAME) VALUES (283, 'USER');
INSERT INTO PUBLIC.USERS_AUTHORITIES (USERS_ID, AUTHORITIES_NAME) VALUES (284, 'ADMIN');
INSERT INTO PUBLIC.USERS_AUTHORITIES (USERS_ID, AUTHORITIES_NAME) VALUES (348, 'USER');

INSERT INTO PUBLIC.SUBJECTS (ID, DESCRIPTION, NAME) VALUES (98, 'only for Shrekos', 'Shrek sub');
INSERT INTO PUBLIC.SUBJECTS (ID, DESCRIPTION, NAME) VALUES (113, 'Ici, lâchez vos plus beaux memes', 'Meme land');
INSERT INTO PUBLIC.SUBJECTS (ID, DESCRIPTION, NAME) VALUES (199, 'ici que le meilleur du RAP', 'Rap caviar');
INSERT INTO PUBLIC.SUBJECTS (ID, DESCRIPTION, NAME) VALUES (206, 'C''est quand même bien drôle', 'OSS117-QuelMonstre');
INSERT INTO PUBLIC.SUBJECTS (ID, DESCRIPTION, NAME) VALUES (231, 'Les pros codeurs -> c''est par ici', 'Coding');
INSERT INTO PUBLIC.SUBJECTS (ID, DESCRIPTION, NAME) VALUES (239, 'Un peu d''humour ca ne fait pas de mal', 'Humour devs');

INSERT INTO PUBLIC.POSTS (POST_ID, CREATED_DATE, DESCRIPTION, POST_NAME, URL, SUB_ID, USER_ID) VALUES (100, '2021-03-29 03:00:39.193469', '<p>Bonjour &agrave; tous,</p>

<p>voici une s&eacute;ance de m&eacute;ditation bas&eacute;e sur l&#39;hypnotisation. Elle m&#39;a &eacute;t&eacute; conseill&eacute;e par ma psychologue qui est du m&ecirc;me signe astrologique que moi (Vierge ascendant G&eacute;meaux) et qui est donc par d&eacute;finition une personne de confiance.<br />
Il vous suffit de regarder intensemment l&#39;image suivante pendant de longues secondes:</p>

<p><img alt="" src="https://i.pinimg.com/originals/54/39/35/5439351e691652bfa38cd3930204a776.gif" style="height:216px; width:384px" /></p>
', 'Shrek Séance d''hypnotisation', '', 98, 97);
INSERT INTO PUBLIC.POSTS (POST_ID, CREATED_DATE, DESCRIPTION, POST_NAME, URL, SUB_ID, USER_ID) VALUES (101, '2021-03-29 03:02:40.718083', '<p><img alt="" src="https://i.pinimg.com/originals/d7/ff/05/d7ff059defda318410145c5920131c1f.jpg" style="height:335px; width:440px" /></p>

<p>Ici, que des photos de ce bg.</p>

<p>Montrons au monde entier qui est le fashion-ogre le plus styl&eacute; de l&#39;histoire</p>
', 'Shrek le Beau gosse', '', 98, 97);
INSERT INTO PUBLIC.POSTS (POST_ID, CREATED_DATE, DESCRIPTION, POST_NAME, URL, SUB_ID, USER_ID) VALUES (102, '2021-03-29 03:04:23.699126', '<table border="1" cellpadding="1" cellspacing="1" style="width:500px">
	<tbody>
		<tr>
			<td>Shrek beau</td>
			<td>Shrek heureux</td>
		</tr>
		<tr>
			<td>Shrek Triste</td>
			<td>Shrek moche</td>
		</tr>
	</tbody>
</table>

<p>Voici ci-contre la grille du Shrekos. Initi&eacute;e par le psychologue informaticien et multi-analyste math&eacute;maticien Java fan: Remi Foreuse.</p>

<p>Qu&#39;en pensez vous ?</p>
', 'Shrek statistique', '', 98, 97);
INSERT INTO PUBLIC.POSTS (POST_ID, CREATED_DATE, DESCRIPTION, POST_NAME, URL, SUB_ID, USER_ID) VALUES (114, '2021-03-29 03:13:15.263080', '<p>Ici, l&acirc;chez vos meilleurs meme de Walter.</p>

<p>Je commence, Walter et sa clique:</p>

<p><img alt="" src="https://images-na.ssl-images-amazon.com/images/I/31CaD7sYc4L._SX331_BO1,204,203,200_.jpg" style="height:499px; width:333px" /></p>
', 'Walter le sang', '', 113, 97);
INSERT INTO PUBLIC.POSTS (POST_ID, CREATED_DATE, DESCRIPTION, POST_NAME, URL, SUB_ID, USER_ID) VALUES (116, '2021-03-29 03:15:47.163162', '<p>Notre t&ecirc;te apr&egrave;s avoir pass&eacute; de longues heures sur le projets</p>

<p><img alt="" src="https://ih1.redbubble.net/image.698410235.0273/st,small,507x507-pad,600x600,f8f8f8.u2.jpg" style="height:600px; width:600px" /></p>
', 'Développeurs en détresse', '', 113, 97);
INSERT INTO PUBLIC.POSTS (POST_ID, CREATED_DATE, DESCRIPTION, POST_NAME, URL, SUB_ID, USER_ID) VALUES (117, '2021-03-29 03:16:41.445293', '<p>Flow / 20</p>

<p><img alt="" src="https://pbs.twimg.com/media/EVZQFT5UUAAg0G7.jpg" style="height:791px; width:795px" /></p>
', 'Gucci Dogs', '', 113, 97);
INSERT INTO PUBLIC.POSTS (POST_ID, CREATED_DATE, DESCRIPTION, POST_NAME, URL, SUB_ID, USER_ID) VALUES (119, '2021-03-29 03:19:21.299274', '<p><img alt="" src="https://thumbs.gfycat.com/BoldFlickeringAmericanshorthair-size_restricted.gif" style="height:250px; width:416px" /></p>
', 'Grave beau ce petit site non ? ', '', 113, 97);
INSERT INTO PUBLIC.POSTS (POST_ID, CREATED_DATE, DESCRIPTION, POST_NAME, URL, SUB_ID, USER_ID) VALUES (123, '2021-03-29 03:24:20.601692', '<p>Regardez c&#39;est trop triste</p>

<p><img alt="" src="https://media.tenor.com/images/08acd9452b99d4b049a0a42269821cda/tenor.gif" style="height:472px; width:220px" /></p>
', 'Un chien met fin à ses jours 😢', '', 113, 97);
INSERT INTO PUBLIC.POSTS (POST_ID, CREATED_DATE, DESCRIPTION, POST_NAME, URL, SUB_ID, USER_ID) VALUES (130, '2021-03-29 03:28:19.341237', '<p>Le site N!</p>

<p><img alt="" src="https://pbs.twimg.com/media/ES-voiuU0AAzlbv.jpg" style="height:1200px; width:840px" /></p>
', 'Le site est enfin sortis ?!', '', 113, 97);
INSERT INTO PUBLIC.POSTS (POST_ID, CREATED_DATE, DESCRIPTION, POST_NAME, URL, SUB_ID, USER_ID) VALUES (200, '2021-03-29 15:00:29.663400', '<p><img alt="" src="https://cdn.radiofrance.fr/s3/cruiser-production/2019/03/ebc4ecd0-dbd2-411d-aba3-5daffbf86bc9/1200x680_au_dd_3.jpg" style="height:680px; width:1200px" /></p>

<p>Je viens de d&eacute;couvrir ce son, une dinguerie non ?</p>
', '*** *** ******** de l''Himalaya', '', 199, 97);
INSERT INTO PUBLIC.POSTS (POST_ID, CREATED_DATE, DESCRIPTION, POST_NAME, URL, SUB_ID, USER_ID) VALUES (202, '2021-03-29 15:02:31.211223', '<p>Un pur album j&#39;ai a-do-r&eacute;</p>
', 'JVLIVS II, une douceur', '', 199, 97);
INSERT INTO PUBLIC.POSTS (POST_ID, CREATED_DATE, DESCRIPTION, POST_NAME, URL, SUB_ID, USER_ID) VALUES (204, '2021-03-29 15:03:29.043122', '<p>balancez son prefs de JuJuJul</p>
', 'Jul le sang', '', 199, 97);
INSERT INTO PUBLIC.POSTS (POST_ID, CREATED_DATE, DESCRIPTION, POST_NAME, URL, SUB_ID, USER_ID) VALUES (208, '2021-03-29 15:20:37.438740', '<p>BD Energy / 20 ?</p>

<p>&nbsp;</p>

<p><img alt="" src="https://i.pinimg.com/originals/db/cc/dc/dbccdc04a459f22131f3c9b5bfb194d7.gif" /></p>
', 'Memes OSS 117', '', 206, 205);
INSERT INTO PUBLIC.POSTS (POST_ID, CREATED_DATE, DESCRIPTION, POST_NAME, URL, SUB_ID, USER_ID) VALUES (220, '2021-03-29 15:27:45.866542', '<p><img alt="" src="https://media.tenor.com/images/01195a62fd994e467f9ac08433683fc7/tenor.gif" /></p>
', 'MERCI AUX CLIENTS FIDELES', '', 199, 205);
INSERT INTO PUBLIC.POSTS (POST_ID, CREATED_DATE, DESCRIPTION, POST_NAME, URL, SUB_ID, USER_ID) VALUES (232, '2021-03-29 15:33:52.919001', '<p>I am currently implementing an OpenID authentication based on <a href="https://github.com/fromi/spring-google-openidconnect">this</a> example. Now I am developing behind a network proxy, therefore the server cannot connect to google. The java proxy settings seem to not have any effect. I also found <a href="https://stackoverflow.com/questions/24605372/proxy-settings-in-spring-boot">this</a> stackoverflow question, but I cannot figure out where to put the code. How can I configure the proxy for my spring boot container?</p>

<p>thanks</p>
', 'Spring-Boot behind a network proxy', '', 231, 97);
INSERT INTO PUBLIC.POSTS (POST_ID, CREATED_DATE, DESCRIPTION, POST_NAME, URL, SUB_ID, USER_ID) VALUES (240, '2021-03-29 15:39:48.275699', '<p><img alt="" src="https://pbs.twimg.com/media/ELvYLZEWsAA8JJ3.jpg" style="height:754px; width:973px" /></p>
', 'Le debugger utile?', '', 239, 213);
INSERT INTO PUBLIC.POSTS (POST_ID, CREATED_DATE, DESCRIPTION, POST_NAME, URL, SUB_ID, USER_ID) VALUES (249, '2021-03-29 15:55:42.698999', '<p><img alt="" src="https://i.pinimg.com/originals/f7/f9/de/f7f9de9e8391fd17e53e97fd1dc9ee3d.jpg" style="height:516px; width:700px" /></p>
', 'Mon travail', '', 239, 213);
INSERT INTO PUBLIC.POSTS (POST_ID, CREATED_DATE, DESCRIPTION, POST_NAME, URL, SUB_ID, USER_ID) VALUES (250, '2021-03-29 16:04:23.937655', '<p><img alt="" src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSJmBr9M83OL76o7eyzWsosS-XLfaMREUD4ww&amp;usqp=CAU" style="height:242px; width:209px" /></p>
', 'Moi j''ai trouvé la blague excellente 🙂', '', 239, 213);
INSERT INTO PUBLIC.POSTS (POST_ID, CREATED_DATE, DESCRIPTION, POST_NAME, URL, SUB_ID, USER_ID) VALUES (251, '2021-03-29 16:11:51.441245', '<p><img alt="" src="https://i.redd.it/o708snz01d821.jpg" style="height:531px; width:451px" /></p>
', 'On fait attention a vos yeux', '', 113, 213);
INSERT INTO PUBLIC.POSTS (POST_ID, CREATED_DATE, DESCRIPTION, POST_NAME, URL, SUB_ID, USER_ID) VALUES (316, '2021-03-30 00:14:10.871980', '<p><strong>Le vendeur de toilettes britannique <em>Plumbworld</em> vient de publier un recueil de recettes de cocktails qui substituent l&rsquo;alcool par de l&rsquo;urine.</strong></p>

<p><em>Beurk.</em></p>

<p>Le livre a pour but de promouvoir l&rsquo;urinoth&eacute;rapie, la pratique qui consiste &agrave; consommer de l&rsquo;urine pour exploiter ses bienfaits. Le r&eacute;gime est plus connu en Chine et en Inde, mais il est tout de m&ecirc;me r&eacute;pandu &agrave; travers le monde.</p>

<p>L&rsquo;&eacute;mission &laquo;Cam&eacute;ra 89&raquo; avait m&ecirc;me r&eacute;alis&eacute; un reportage sur les Qu&eacute;b&eacute;cois qui la pratiquent.</p>
', 'Cocktail à base ', '', 113, 205);
INSERT INTO PUBLIC.POSTS (POST_ID, CREATED_DATE, DESCRIPTION, POST_NAME, URL, SUB_ID, USER_ID) VALUES (448, '2021-03-30 09:35:00.523460', '<p>vos meilleurs sons ?</p>
', 'J''adore le rap', '', 199, 97);

INSERT INTO PUBLIC.POST_VOTES (ID, CREATION_DATE, TYPE, POST_ID, USER_ID) VALUES (103, '2021-03-29 03:05:37.183554', 'UPVOTE', 100, 33);
INSERT INTO PUBLIC.POST_VOTES (ID, CREATION_DATE, TYPE, POST_ID, USER_ID) VALUES (105, '2021-03-29 03:06:18.531465', 'UPVOTE', 101, 33);
INSERT INTO PUBLIC.POST_VOTES (ID, CREATION_DATE, TYPE, POST_ID, USER_ID) VALUES (107, '2021-03-29 03:07:34.115952', 'UPVOTE', 101, 97);
INSERT INTO PUBLIC.POST_VOTES (ID, CREATION_DATE, TYPE, POST_ID, USER_ID) VALUES (122, '2021-03-29 03:24:03.931061', 'UPVOTE', 119, 33);
INSERT INTO PUBLIC.POST_VOTES (ID, CREATION_DATE, TYPE, POST_ID, USER_ID) VALUES (129, '2021-03-29 03:25:54.492601', 'UPVOTE', 123, 97);
INSERT INTO PUBLIC.POST_VOTES (ID, CREATION_DATE, TYPE, POST_ID, USER_ID) VALUES (163, '2021-03-29 04:10:33.959795', 'UPVOTE', 130, 33);
INSERT INTO PUBLIC.POST_VOTES (ID, CREATION_DATE, TYPE, POST_ID, USER_ID) VALUES (198, '2021-03-29 14:54:58.180673', 'UPVOTE', 119, 97);
INSERT INTO PUBLIC.POST_VOTES (ID, CREATION_DATE, TYPE, POST_ID, USER_ID) VALUES (203, '2021-03-29 15:02:43.927462', 'UPVOTE', 200, 97);
INSERT INTO PUBLIC.POST_VOTES (ID, CREATION_DATE, TYPE, POST_ID, USER_ID) VALUES (209, '2021-03-29 15:20:44.895588', 'UPVOTE', 208, 205);
INSERT INTO PUBLIC.POST_VOTES (ID, CREATION_DATE, TYPE, POST_ID, USER_ID) VALUES (214, '2021-03-29 15:24:32.927366', 'UPVOTE', 208, 97);
INSERT INTO PUBLIC.POST_VOTES (ID, CREATION_DATE, TYPE, POST_ID, USER_ID) VALUES (215, '2021-03-29 15:26:21.948689', 'UPVOTE', 119, 205);
INSERT INTO PUBLIC.POST_VOTES (ID, CREATION_DATE, TYPE, POST_ID, USER_ID) VALUES (216, '2021-03-29 15:26:25.462041', 'UPVOTE', 101, 205);
INSERT INTO PUBLIC.POST_VOTES (ID, CREATION_DATE, TYPE, POST_ID, USER_ID) VALUES (217, '2021-03-29 15:26:34.947366', 'DOWNVOTE', 200, 205);
INSERT INTO PUBLIC.POST_VOTES (ID, CREATION_DATE, TYPE, POST_ID, USER_ID) VALUES (218, '2021-03-29 15:26:52.713113', 'UPVOTE', 202, 205);
INSERT INTO PUBLIC.POST_VOTES (ID, CREATION_DATE, TYPE, POST_ID, USER_ID) VALUES (219, '2021-03-29 15:26:53.530558', 'UPVOTE', 204, 205);
INSERT INTO PUBLIC.POST_VOTES (ID, CREATION_DATE, TYPE, POST_ID, USER_ID) VALUES (222, '2021-03-29 15:27:54.420873', 'UPVOTE', 220, 205);
INSERT INTO PUBLIC.POST_VOTES (ID, CREATION_DATE, TYPE, POST_ID, USER_ID) VALUES (226, '2021-03-29 15:29:09.203126', 'UPVOTE', 220, 213);
INSERT INTO PUBLIC.POST_VOTES (ID, CREATION_DATE, TYPE, POST_ID, USER_ID) VALUES (229, '2021-03-29 15:30:00.907735', 'UPVOTE', 114, 97);
INSERT INTO PUBLIC.POST_VOTES (ID, CREATION_DATE, TYPE, POST_ID, USER_ID) VALUES (243, '2021-03-29 15:41:19.158692', 'UPVOTE', 232, 233);
INSERT INTO PUBLIC.POST_VOTES (ID, CREATION_DATE, TYPE, POST_ID, USER_ID) VALUES (254, '2021-03-29 16:28:18.168706', 'UPVOTE', 220, 33);
INSERT INTO PUBLIC.POST_VOTES (ID, CREATION_DATE, TYPE, POST_ID, USER_ID) VALUES (257, '2021-03-29 16:40:46.485942', 'UPVOTE', 249, 97);
INSERT INTO PUBLIC.POST_VOTES (ID, CREATION_DATE, TYPE, POST_ID, USER_ID) VALUES (259, '2021-03-29 16:41:01.208040', 'UPVOTE', 251, 97);
INSERT INTO PUBLIC.POST_VOTES (ID, CREATION_DATE, TYPE, POST_ID, USER_ID) VALUES (260, '2021-03-29 16:41:05.818701', 'DOWNVOTE', 250, 97);
INSERT INTO PUBLIC.POST_VOTES (ID, CREATION_DATE, TYPE, POST_ID, USER_ID) VALUES (317, '2021-03-30 00:14:25.225130', 'DOWNVOTE', 316, 205);
INSERT INTO PUBLIC.POST_VOTES (ID, CREATION_DATE, TYPE, POST_ID, USER_ID) VALUES (351, '2021-03-30 01:24:39.222862', 'UPVOTE', 220, 348);
INSERT INTO PUBLIC.POST_VOTES (ID, CREATION_DATE, TYPE, POST_ID, USER_ID) VALUES (355, '2021-03-30 01:25:31.850328', 'UPVOTE', 119, 348);
INSERT INTO PUBLIC.POST_VOTES (ID, CREATION_DATE, TYPE, POST_ID, USER_ID) VALUES (380, '2021-03-30 02:13:23.543401', 'DOWNVOTE', 316, 33);
INSERT INTO PUBLIC.POST_VOTES (ID, CREATION_DATE, TYPE, POST_ID, USER_ID) VALUES (446, '2021-03-30 09:14:03.159187', 'UPVOTE', 220, 97);

INSERT INTO PUBLIC.COMMENTS (ID, CREATION_DATE, TEXT, POST_ID, SUPER_COMMENT_ID, USER_ID) VALUES (104, '2021-03-29 03:05:42.562445', '<p>Jsuis hypnotis&eacute;</p>
', 100, null, 97);
INSERT INTO PUBLIC.COMMENTS (ID, CREATION_DATE, TEXT, POST_ID, SUPER_COMMENT_ID, USER_ID) VALUES (106, '2021-03-29 03:06:57.011817', '<p>Sah quel beau gosse</p>
', 101, null, 33);
INSERT INTO PUBLIC.COMMENTS (ID, CREATION_DATE, TEXT, POST_ID, SUPER_COMMENT_ID, USER_ID) VALUES (108, '2021-03-29 03:07:59.886595', '<p>t&#39;as bien raison, son ptit minois l&agrave;</p>
', 101, 106, 97);
INSERT INTO PUBLIC.COMMENTS (ID, CREATION_DATE, TEXT, POST_ID, SUPER_COMMENT_ID, USER_ID) VALUES (115, '2021-03-29 03:13:45.869229', '<p>Walter s&#39;est fait un d&eacute;grad&eacute;:</p>

<p><img alt="" src="https://images-na.ssl-images-amazon.com/images/I/41tKs-+D6aL._SX331_BO1,204,203,200_.jpg" style="height:499px; width:333px" /></p>
', 114, null, 97);
INSERT INTO PUBLIC.COMMENTS (ID, CREATION_DATE, TEXT, POST_ID, SUPER_COMMENT_ID, USER_ID) VALUES (118, '2021-03-29 03:19:01.522676', '<p>le projet*</p>
', 116, null, 33);
INSERT INTO PUBLIC.COMMENTS (ID, CREATION_DATE, TEXT, POST_ID, SUPER_COMMENT_ID, USER_ID) VALUES (120, '2021-03-29 03:22:56.603250', '<p>Walter &agrave; pass&eacute; une mauvaise nuit</p>

<p><img alt="" src="https://media.tenor.com/images/e2247ba024b6e44f535e7186b52f2eac/tenor.gif" style="height:284px; width:220px" /></p>
', 114, null, 97);
INSERT INTO PUBLIC.COMMENTS (ID, CREATION_DATE, TEXT, POST_ID, SUPER_COMMENT_ID, USER_ID) VALUES (121, '2021-03-29 03:23:08.725362', '<p>un d&eacute;grad&eacute; d&eacute;gradant</p>
', 114, 115, 97);
INSERT INTO PUBLIC.COMMENTS (ID, CREATION_DATE, TEXT, POST_ID, SUPER_COMMENT_ID, USER_ID) VALUES (124, '2021-03-29 03:24:37.568328', '<p>mais c&#39;est Walter !!!!</p>
', 123, null, 97);
INSERT INTO PUBLIC.COMMENTS (ID, CREATION_DATE, TEXT, POST_ID, SUPER_COMMENT_ID, USER_ID) VALUES (126, '2021-03-29 03:25:16.733644', '<p>I&#39;m in love&nbsp;</p>
', 119, null, 33);
INSERT INTO PUBLIC.COMMENTS (ID, CREATION_DATE, TEXT, POST_ID, SUPER_COMMENT_ID, USER_ID) VALUES (210, '2021-03-29 15:21:23.015885', '<p>jconnais pas ce film mais il est beau l&agrave; son ptit sourrire miam 😏</p>

<p>19/20</p>
', 208, null, 97);
INSERT INTO PUBLIC.COMMENTS (ID, CREATION_DATE, TEXT, POST_ID, SUPER_COMMENT_ID, USER_ID) VALUES (211, '2021-03-29 15:21:43.181640', '<p>21/20 direct, comme notre note au projet de back-end</p>

<p>&nbsp;</p>

<p><img alt="" src="https://media.tenor.com/images/cc9141caf5873045cd5a878bf1f297a6/tenor.gif" /></p>
', 208, null, 205);
INSERT INTO PUBLIC.COMMENTS (ID, CREATION_DATE, TEXT, POST_ID, SUPER_COMMENT_ID, USER_ID) VALUES (223, '2021-03-29 15:28:09.121094', '<p>Des rafales dans l&#39;escalier</p>
', 220, null, 97);
INSERT INTO PUBLIC.COMMENTS (ID, CREATION_DATE, TEXT, POST_ID, SUPER_COMMENT_ID, USER_ID) VALUES (230, '2021-03-29 15:31:50.028946', '<p>JCVD, la base, vraiment meilleur son</p>
', 204, null, 97);
INSERT INTO PUBLIC.COMMENTS (ID, CREATION_DATE, TEXT, POST_ID, SUPER_COMMENT_ID, USER_ID) VALUES (234, '2021-03-29 15:35:05.319276', '<p>Not sure if this is of any use, but I&#39;m just working through a Spring Boot tutorial currently (<a href="https://spring.io/guides/gs/integration/">https://spring.io/guides/gs/integration/</a>) and hit a similar network proxy issue. This was resolved just by providing the JVM arguments</p>

<div style="background:#eeeeee;border:1px solid #cccccc;padding:5px 10px;">
<pre>
<code>-Dhttp.proxyHost=your.proxy.net -Dhttp.proxyPort=8080</code></pre>
</div>
', 232, null, 233);
INSERT INTO PUBLIC.COMMENTS (ID, CREATION_DATE, TEXT, POST_ID, SUPER_COMMENT_ID, USER_ID) VALUES (236, '2021-03-29 15:36:15.608719', '<p>For me, <code>server.use-forwarded-headers=true</code> in <code>application.properties</code> solved the problem.</p>
', 232, null, 235);
INSERT INTO PUBLIC.COMMENTS (ID, CREATION_DATE, TEXT, POST_ID, SUPER_COMMENT_ID, USER_ID) VALUES (237, '2021-03-29 15:36:28.866460', '<p>Zero effect by me.</p>
', 232, 234, 235);
INSERT INTO PUBLIC.COMMENTS (ID, CREATION_DATE, TEXT, POST_ID, SUPER_COMMENT_ID, USER_ID) VALUES (238, '2021-03-29 15:37:11.273229', '<p>If you need this to make a call to an external service, then try to set proxy to the Client you are using (RestTemplate, etc), as below:</p>

<div style="background:#eeeeee;border:1px solid #cccccc;padding:5px 10px;">
<pre>
<code>HttpComponentsClientHttpRequestFactory requestFactory = new 
HttpComponentsClientHttpRequestFactory();
DefaultHttpClient httpClient = (DefaultHttpClient) requestFactory.getHttpClient();
HttpHost proxy = new HttpHost(&quot;proxtserver&quot;, port);
httpClient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY,proxy);
restTemplate.setRequestFactory(requestFactory);</code></pre>
</div>
', 232, null, 235);
INSERT INTO PUBLIC.COMMENTS (ID, CREATION_DATE, TEXT, POST_ID, SUPER_COMMENT_ID, USER_ID) VALUES (246, '2021-03-29 15:41:56.934549', '<p>solve my problem ! Thanks</p>
', 232, 234, 97);
INSERT INTO PUBLIC.COMMENTS (ID, CREATION_DATE, TEXT, POST_ID, SUPER_COMMENT_ID, USER_ID) VALUES (248, '2021-03-29 15:43:22.282396', '<p>hahaha j&#39;aime l&#39;humour</p>
', 240, null, 97);
INSERT INTO PUBLIC.COMMENTS (ID, CREATION_DATE, TEXT, POST_ID, SUPER_COMMENT_ID, USER_ID) VALUES (252, '2021-03-29 16:26:37.522871', '<p>Did you try to restart your computer ?</p>
', 232, null, 33);
INSERT INTO PUBLIC.COMMENTS (ID, CREATION_DATE, TEXT, POST_ID, SUPER_COMMENT_ID, USER_ID) VALUES (258, '2021-03-29 16:40:52.841436', '<p>excellent</p>
', 249, null, 97);
INSERT INTO PUBLIC.COMMENTS (ID, CREATION_DATE, TEXT, POST_ID, SUPER_COMMENT_ID, USER_ID) VALUES (268, '2021-03-29 19:13:48.672926', '<p>the reponse of DarkSasuke77 worked for me</p>
', 232, 252, 97);
INSERT INTO PUBLIC.COMMENTS (ID, CREATION_DATE, TEXT, POST_ID, SUPER_COMMENT_ID, USER_ID) VALUES (449, '2021-03-30 09:40:52.574518', '<p>Les clients sont hyper fid&egrave;les</p>
', 220, null, 97);
INSERT INTO PUBLIC.COMMENTS (ID, CREATION_DATE, TEXT, POST_ID, SUPER_COMMENT_ID, USER_ID) VALUES (450, '2021-03-30 09:41:15.383700', '<p>ca rafale</p>
', 220, 223, 97);

INSERT INTO PUBLIC.COMMENT_VOTES (ID, CREATION_DATE, TYPE, COMMENT_ID, USER_ID) VALUES (212, '2021-03-29 15:22:47.958488', 'UPVOTE', 210, 205);
INSERT INTO PUBLIC.COMMENT_VOTES (ID, CREATION_DATE, TYPE, COMMENT_ID, USER_ID) VALUES (241, '2021-03-29 15:41:12.284332', 'UPVOTE', 234, 233);
INSERT INTO PUBLIC.COMMENT_VOTES (ID, CREATION_DATE, TYPE, COMMENT_ID, USER_ID) VALUES (242, '2021-03-29 15:41:15.670806', 'DOWNVOTE', 237, 233);
INSERT INTO PUBLIC.COMMENT_VOTES (ID, CREATION_DATE, TYPE, COMMENT_ID, USER_ID) VALUES (244, '2021-03-29 15:41:43.340794', 'UPVOTE', 234, 97);
INSERT INTO PUBLIC.COMMENT_VOTES (ID, CREATION_DATE, TYPE, COMMENT_ID, USER_ID) VALUES (245, '2021-03-29 15:41:47.458826', 'DOWNVOTE', 237, 97);
INSERT INTO PUBLIC.COMMENT_VOTES (ID, CREATION_DATE, TYPE, COMMENT_ID, USER_ID) VALUES (247, '2021-03-29 15:42:11.667498', 'DOWNVOTE', 238, 97);
INSERT INTO PUBLIC.COMMENT_VOTES (ID, CREATION_DATE, TYPE, COMMENT_ID, USER_ID) VALUES (253, '2021-03-29 16:28:09.692309', 'UPVOTE', 234, 33);
INSERT INTO PUBLIC.COMMENT_VOTES (ID, CREATION_DATE, TYPE, COMMENT_ID, USER_ID) VALUES (267, '2021-03-29 19:13:15.700143', 'UPVOTE', 252, 97);