set names 'utf8';
use sidestory_db;

create table novel
(
    id                bigint       not null
        primary key,
    novel_attention   varchar(255) null,
    novel_author      varchar(255) null,
    novel_content     varchar(255) null,
    novel_description text null,
    novel_genre       varchar(255) null,
    novel_img         varchar(255) null,
    novel_name        varchar(255) null,
    novel_rating      varchar(255) null,
    rating_age        int          null,
    novelurl         varchar(255) null
);

create table pages
(
    id           bigint       not null
        primary key,
    name_source  varchar(255) null,
    novel_id     bigint       null,
    source       varchar(255) null,
    start_source bit          null
);


insert into novel value ('0', 'насилие, гуро, восхождение с низов', 'Ван Ю', 'table_of_contents/adventure_content.html', 'Бедный и обычный мальчик из деревни присоединяется к небольшой секте в Цзян Ху и случайно становится Неофициальным Учеником. Как Хань Ли, простолюдин по происхождению, создаст для себя точку опоры в его секте? Как он, со своими посредственными способностями, сможет успешно пройти путь культивации и стать бессмертным? Эта история об обычном смертном, который, несмотря на все трудности, сразится с демонами и древними небожителями для того, чтобы найти свой собственный путь к бессмертию.', 'новелла, приключение, боевые искусства, уся', 'puteshestvie-k-bessmertiyu.jpg', 'Путешествие к бессмертию', 'R','16', 'adventure_to_immortal.html');
insert into novel value ('1', 'насилие, смерть основного персонажа, гуро, смерть второстепенных персонажей, трагичное прошлое', 'Мосян Тунсю', 'table_of_contents/magistr_content.html', 'Когда-то давно Основатель Пути Тьмы Вэй Уcянь странствовал по свету, творя невообразимые бесчинства и хаос, за что миллионы людей ненавидели его. В конце концов он был предан своим шиди и убит союзом кланов, объединившихся, чтобы сокрушить его. Вэй Усянь переродился в теле чудака, от которого отказался родной клан. Позже его против воли забрал к себе Лань Ванцзи — его давний знакомый. <br> Так началась захватывающая история, полная сражений с монстрами, решения загадок и обучения подрастающего поколения. Флиртуя с Лань Ванцзи, Вэй Усянь постепенно осознает, что обычно высокомерный и сдержанный, Лань Ванцзи таит к нему особые чувства.', 'новелла, приключение, драма, детектив, боевые искусства, сянься, фэнтези, комедия, сверхъестественное', 'img/the_founder_of_diabolic.jpg', 'Магистр дьявольского культа', 'NC-17','18','magistr_info.html');
insert into novel value ('2', 'насилие, гендерная интрига', 'Лао Хули, Бо Му, Мань Гунчан', 'table_of_contents/premier_content.html', 'Ради своей младшей сестры талантливый мужчина, переодевшись в женщину, проник во дворец, чтобы добиться благосклонности императора. Что он сделает, попав в его жестокий и опасный гарем? И какую историю он придумает для Императора и Премьер-Министра? Он не только должен бороться за свои права, но также влюбить в себя императора ради сестры. У него получится? И что же случится после того, как Император узнает правду?', 'маньхуа, приключение, драма, боевые искусства', 'prime-minister-in-disguise.jpg', 'Премьер-министр под прекрытием', 'NC-17','18', 'prime_minister_info.html');
insert into novel value ('3', 'насилие, возрождение, гуро, попаданец, расы, hurt/comfort', 'Мосян Тунсю', 'table_of_contents/system_content.html', '«С каких пор меня тошнит от гаремных романов?» <br> Литературный критик Шэнь Юань перерождается в одного из героев ненавистного романа - гнусного злодея Шэнь Цинцю. Которому, само собой, суждено быть казненным главным героем с особой жестокостью. <br> «Не то чтобы я мечтал броситься на шею главному герою при чтении, но какого черта он ведет себя подобным образом? И с какой радости все романтические линии замыкаются на меня?» <br> Он жаждет доказать, что даже злодеи имеют право на жизнь, да еще какую!', 'новелла, приключение, драма, попаданчество, боевые искусства, уся/сянься, фэнтези', 'scumbag-villians-self-saving-system.jpg', 'Система "Спаси-себя-сам" для главного злодея', 'NC-17','18','system_info.html');
insert into novel value ('4', 'заговоры, политика, месть', 'Хай Янь', 'table_of_contents/langya_content.html', 'История разворачивается вокруг заговора против старшего сына императора и семьи Линь, глава которой командует армией Великой Лян. Они обвинены в измене и приговорены к смерти. Линь Шу, девятнадцатилетний командующий одного из отрядов, выживает и скрывается в Цзянху, мире бойцов. Приняв личность ученого Мэй Чансу, он основывает союз Цзянцзо и устраивает свои дела вдали от двора, готовясь отомстить. Сильно подорванное здоровье заставляет его отказаться от физических методов и заняться деятельностью стратега. Двенадцать лет спустя он возвращается в столицу под вымышленным именем, чтобы вмешаться в борьбу сыновей императора за престол. Он принимает решение поддержать принца Цзина, когда-то своего лучшего друга, который не участвует в политических делах. Принц Цзин не верит в обвинения против семьи Линь и хочет добиться оправдания, но из-за резких речей и поступков он считается опальным принцем и практически не имеет власти. Между ним и троном стоят старшие братья — наследный принц, сын фаворитки императора, и принц Юй, выращенный императрицей. Давние враги семьи Линь, подготовившие заговор, занимают высокие посты и вмешиваются в придворные игры, а внешнеполитическая обстановка медленно, но верно накаляется.', 'новелла, история, драма, детектив, боевые искусства, уся, трагедия', 'langya_bang.jpg', 'Путешествие к бессмертию', 'R','16','langya_info.html');
insert into novel value ('5', 'насилие, жестокость, слоубёрн, политические интриги', 'Priest', 'table_of_contents/wolf_content.html', 'В эпоху Великой Лян жизнь людей стала комфортнее благодаря паровым машинам, работающим на топливе под названием «цзылюцзинь». Чан Гэн, который провел всё детство в небольшом городке, имел не самые лучшие отношения с матерью, а отчим наведывался домой всего несколько раз в год. Его единственными друзьями были двое маленьких детей, учитель и его ифу — приемный отец. Но однажды его жизнь перевернулась с ног на голову. После вторжения варварских племен Чан Гэн узнал, что вся его жизнь, личность, мать, учитель и даже его любимый ифу — сплошная ложь.', 'новелла, история, драма, политика, детектив', 'killing_the_wolf.jpg', 'Убить волка', 'NC-17','18','wolf_info.html');
insert into novel value ('6', 'насилие, возрождение, гуро, безнравственность, сомнительные принципы', 'Жоубао Бучи Жоу', 'table_of_contents/erha_content.html', 'Мо Жань чувствовал, что выбрать Чу Ваньнина учителем было большой ошибкой. <br> По правде говоря, Уважаемый Учитель слишком уж похож на кота, а сам он – на постоянно крутящегося под ногами и виляющего хвостом глупого щенка. <br> Псы и коты относятся к разным видам и не могут быть вместе, и глупый пес изначально вовсе не планировал тянуть к коту свои мохнатые лапы. <br> Он нутром чуял, что раз уж родился собакой, то должен выбрать себе в пару другую собаку. Вот взять к примеру его старшего брата-наставника, похожего на прелестного шпица, вместе они составили бы идеальную пару', 'новелла, приключение, драма, философия, боевые искусства', 'erha.jpg', 'Хаски и его учитель-кот', 'NC-17','18','erha_info.html');




