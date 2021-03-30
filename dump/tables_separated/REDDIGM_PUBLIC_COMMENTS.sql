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