set names 'utf8';
use sidestory_db;

create table novel
(
    id                bigint       not null
        primary key,
    novel_attention   varchar(255) null,
    novel_author      varchar(255) null,
    novel_description text null,
    novel_genre       varchar(255) null,
    novel_img         varchar(255) null,
    novel_name        varchar(255) null,
    novel_rating      varchar(255) null,
    rating_age        int          null,
    novelurl         varchar(255) null,
    constraint UK_e13jg535agtl22krc3633wkv6
        unique (novelurl)
);

create table pages
(
    id                bigint auto_increment
        primary key,
    current_character varchar(255) null,
    name_source       varchar(255) null,
    source            varchar(255) null,
    start_source      bit          null,
    novel_id          bigint       null,
    constraint FKh0iegkt2jap75mdgl3vru9o6e
        foreign key (novel_id) references novel (id)
);

create table roles
(
    id bigint       not null
        primary key,
    name     varchar(255) null
);

create table t_user
(
    id       bigint auto_increment
        primary key,
    active   bit          not null,
    age      int          null,
    email    varchar(255) null,
    img      varchar(255) null,
    password varchar(100) null,
    sex      varchar(255) null,
    username varchar(128) null
);

create table t_roles
(
    user_id  bigint not null,
    roles varchar(255) not null,
    primary key (user_id),
    constraint FKsgq4ifnlnxgq6oea4khaeoier
        foreign key (user_id) references t_user (id)
);

CREATE TABLE SPRING_SESSION (
        PRIMARY_ID CHAR(36) NOT NULL,
        SESSION_ID CHAR(36) NOT NULL,
        CREATION_TIME BIGINT NOT NULL,
        LAST_ACCESS_TIME BIGINT NOT NULL,
        MAX_INACTIVE_INTERVAL INT NOT NULL,
        EXPIRY_TIME BIGINT NOT NULL,
        PRINCIPAL_NAME VARCHAR(100),
        CONSTRAINT SPRING_SESSION_PK PRIMARY KEY (PRIMARY_ID)
) ENGINE=InnoDB ROW_FORMAT=DYNAMIC;

CREATE UNIQUE INDEX SPRING_SESSION_IX1 ON SPRING_SESSION (SESSION_ID);
CREATE INDEX SPRING_SESSION_IX2 ON SPRING_SESSION (EXPIRY_TIME);
CREATE INDEX SPRING_SESSION_IX3 ON SPRING_SESSION (PRINCIPAL_NAME);

CREATE TABLE SPRING_SESSION_ATTRIBUTES (
       SESSION_PRIMARY_ID CHAR(36) NOT NULL,
       ATTRIBUTE_NAME VARCHAR(200) NOT NULL,
       ATTRIBUTE_BYTES BLOB NOT NULL,
       CONSTRAINT SPRING_SESSION_ATTRIBUTES_PK PRIMARY KEY (SESSION_PRIMARY_ID, ATTRIBUTE_NAME),
       CONSTRAINT SPRING_SESSION_ATTRIBUTES_FK FOREIGN KEY (SESSION_PRIMARY_ID) REFERENCES SPRING_SESSION(PRIMARY_ID) ON DELETE CASCADE
) ENGINE=InnoDB ROW_FORMAT=DYNAMIC;


insert into novel value ('1', 'насилие, гуро, восхождение с низов', 'Ван Ю', 'Бедный и обычный мальчик из деревни присоединяется к небольшой секте в Цзян Ху и случайно становится Неофициальным Учеником. Как Хань Ли, простолюдин по происхождению, создаст для себя точку опоры в его секте? Как он, со своими посредственными способностями, сможет успешно пройти путь культивации и стать бессмертным? Эта история об обычном смертном, который, несмотря на все трудности, сразится с демонами и древними небожителями для того, чтобы найти свой собственный путь к бессмертию.', 'новелла, приключение, боевые искусства, уся', 'puteshestvie-k-bessmertiyu.jpg', 'Путешествие к бессмертию', 'R','16', 'adventure_to_immortal');
insert into novel value ('2', 'насилие, смерть основного персонажа, гуро, смерть второстепенных персонажей, трагичное прошлое', 'Мосян Тунсю', 'Когда-то давно Основатель Пути Тьмы Вэй Уcянь странствовал по свету, творя невообразимые бесчинства и хаос, за что миллионы людей ненавидели его. В конце концов он был предан своим шиди и убит союзом кланов, объединившихся, чтобы сокрушить его. Вэй Усянь переродился в теле чудака, от которого отказался родной клан. Позже его против воли забрал к себе Лань Ванцзи — его давний знакомый. Так началась захватывающая история, полная сражений с монстрами, решения загадок и обучения подрастающего поколения. Флиртуя с Лань Ванцзи, Вэй Усянь постепенно осознает, что обычно высокомерный и сдержанный, Лань Ванцзи таит к нему особые чувства.', 'новелла, приключение, драма, детектив, боевые искусства, сянься, фэнтези, комедия, сверхъестественное', 'the_founder_of_diabolic.jpg', 'Магистр дьявольского культа', 'NC-17','18','magistr');
insert into novel value ('3', 'насилие, гендерная интрига', 'Лао Хули, Бо Му, Мань Гунчан', 'Ради своей младшей сестры талантливый мужчина, переодевшись в женщину, проник во дворец, чтобы добиться благосклонности императора. Что он сделает, попав в его жестокий и опасный гарем? И какую историю он придумает для Императора и Премьер-Министра? Он не только должен бороться за свои права, но также влюбить в себя императора ради сестры. У него получится? И что же случится после того, как Император узнает правду?', 'маньхуа, приключение, драма, боевые искусства', 'prime-minister-in-disguise.jpg', 'Премьер-министр под прекрытием', 'NC-17','18', 'minister');
insert into novel value ('4', 'насилие, возрождение, гуро, попаданец, расы, hurt/comfort', 'Мосян Тунсю', '«С каких пор меня тошнит от гаремных романов?» Литературный критик Шэнь Юань перерождается в одного из героев ненавистного романа - гнусного злодея Шэнь Цинцю. Которому, само собой, суждено быть казненным главным героем с особой жестокостью. «Не то чтобы я мечтал броситься на шею главному герою при чтении, но какого черта он ведет себя подобным образом? И с какой радости все романтические линии замыкаются на меня?» Он жаждет доказать, что даже злодеи имеют право на жизнь, да еще какую!', 'новелла, приключение, драма, попаданчество, боевые искусства, уся/сянься, фэнтези', 'scumbag-villians-self-saving-system.jpg', 'Система "Спаси-себя-сам" для главного злодея', 'NC-17','18','system');
insert into novel value ('5', 'заговоры, политика, месть', 'Хай Янь', 'История разворачивается вокруг заговора против старшего сына императора и семьи Линь, глава которой командует армией Великой Лян. Они обвинены в измене и приговорены к смерти. Линь Шу, девятнадцатилетний командующий одного из отрядов, выживает и скрывается в Цзянху, мире бойцов. Приняв личность ученого Мэй Чансу, он основывает союз Цзянцзо и устраивает свои дела вдали от двора, готовясь отомстить. Сильно подорванное здоровье заставляет его отказаться от физических методов и заняться деятельностью стратега. Двенадцать лет спустя он возвращается в столицу под вымышленным именем, чтобы вмешаться в борьбу сыновей императора за престол. Он принимает решение поддержать принца Цзина, когда-то своего лучшего друга, который не участвует в политических делах. Принц Цзин не верит в обвинения против семьи Линь и хочет добиться оправдания, но из-за резких речей и поступков он считается опальным принцем и практически не имеет власти. Между ним и троном стоят старшие братья — наследный принц, сын фаворитки императора, и принц Юй, выращенный императрицей. Давние враги семьи Линь, подготовившие заговор, занимают высокие посты и вмешиваются в придворные игры, а внешнеполитическая обстановка медленно, но верно накаляется.', 'новелла, история, драма, детектив, боевые искусства, уся, трагедия', 'langya_bang.jpg', 'Список архива Ланъя', 'R','16','langya');
insert into novel value ('6', 'насилие, жестокость, слоубёрн, политические интриги', 'Priest', 'В эпоху Великой Лян жизнь людей стала комфортнее благодаря паровым машинам, работающим на топливе под названием «цзылюцзинь». Чан Гэн, который провел всё детство в небольшом городке, имел не самые лучшие отношения с матерью, а отчим наведывался домой всего несколько раз в год. Его единственными друзьями были двое маленьких детей, учитель и его ифу — приемный отец. Но однажды его жизнь перевернулась с ног на голову. После вторжения варварских племен Чан Гэн узнал, что вся его жизнь, личность, мать, учитель и даже его любимый ифу — сплошная ложь.', 'новелла, история, драма, политика, детектив', 'killing_the_wolf.jpg', 'Убить волка', 'NC-17','18','wolf');
insert into novel value ('7', 'насилие, возрождение, гуро, безнравственность, сомнительные принципы', 'Жоубао Бучи Жоу', 'Мо Жань чувствовал, что выбрать Чу Ваньнина учителем было большой ошибкой.  По правде говоря, Уважаемый Учитель слишком уж похож на кота, а сам он – на постоянно крутящегося под ногами и виляющего хвостом глупого щенка. Псы и коты относятся к разным видам и не могут быть вместе, и глупый пес изначально вовсе не планировал тянуть к коту свои мохнатые лапы. Он нутром чуял, что раз уж родился собакой, то должен выбрать себе в пару другую собаку. Вот взять к примеру его старшего брата-наставника, похожего на прелестного шпица, вместе они составили бы идеальную пару', 'новелла, приключение, драма, философия, боевые искусства', 'erha.jpg', 'Хаски и его учитель-кот', 'NC-17','18','erha');

insert into pages value ('1', 'Шэнь Цинцю', 'Глава 1. Переселение в главного злодея', '1.1',b'00001','4');
insert into pages value ('2', 'Шэнь Цинцю', '', '1.2',b'00000','4');
insert into pages value ('3', 'Шэнь Цинцю', '', '1.3.1',b'00000','4');
insert into pages value ('4', 'Шэнь Цинцю', '', '1.3.2',b'00000','4');
insert into pages value ('5', 'Шэнь Цинцю', 'Глава 2. Жестокое обращение над главным героем', '2.1',b'00001','4');
insert into pages value ('6', 'Шэнь Цинцю', '', '2.2.1',b'00000','4');
insert into pages value ('7', 'Шэнь Цинцю', '', '2.2.2',b'00000','4');
insert into pages value ('8', 'Шэнь Цинцю', 'Глава 3. Пушечное мясо наступает', '3.1',b'00001','4');
insert into pages value ('9', 'Шэнь Цинцю', '', '3.2',b'00000','4');
insert into pages value ('10', 'Шэнь Цинцю', 'Глава 4. Тайное содействие главному герою', '4.1',b'00001','4');
insert into pages value ('11', 'Шэнь Цинцю', '', '4.2.1',b'00000','4');
insert into pages value ('12', 'Шэнь Цинцю', '', '4.2.2',b'00000','4');
insert into pages value ('13', 'Шэнь Цинцю', '', '4.3.1',b'00000','4');
insert into pages value ('14', 'Шэнь Цинцю', '', '4.3.2',b'00000','4');
insert into pages value ('15', 'Шэнь Цинцю', 'Глава 5. А вот и миссия', '5.1',b'00001','4');
insert into pages value ('16', 'Шэнь Цинцю', '', '5.2',b'00000','4');

insert into pages value ('17', 'Чан Гэн', 'Глава 1. Граница', '1.1',b'00001','6');
insert into pages value ('18', 'Чан Гэн', '', '1.2',b'00000','6');
insert into pages value ('19', 'Чан Гэн', '', '1.3',b'00000','6');
insert into pages value ('20', 'Чан Гэн', 'Глава 2. Ифу', '2.1',b'00001','6');
insert into pages value ('21', 'Чан Гэн', '', '2.2',b'00000','6');
insert into pages value ('22', 'Чан Гэн', '', '2.3',b'00000','6');
insert into pages value ('23', 'Чан Гэн', 'Глава 3. Прославленный генерал', '3.1',b'00001','6');
insert into pages value ('24', 'Чан Гэн', '', '3.2',b'00000','6');
insert into pages value ('25', 'Чан Гэн', 'Глава 4. Гигантский змей', '4.1',b'00001','6');
insert into pages value ('26', 'Чан Гэн', '', '4.2',b'00000','6');
insert into pages value ('27', 'Чан Гэн', '', '4.3',b'00000','6');

insert into pages value ('28', 'Вей Усянь', 'Глава 1. Пролог', '1',b'00001','2');
insert into pages value ('29', 'Вей Усянь', 'Глава 2. Перерождение', '2.1',b'00001','2');
insert into pages value ('30', 'Вей Усянь', '', '2.2',b'00000','2');
insert into pages value ('31', 'Вей Усянь', 'Глава 3. Нападение. Часть первая', '3.1',b'00001','2');
insert into pages value ('32', 'Вей Усянь', '', '3.2',b'00000','2');
insert into pages value ('33', 'Вей Усянь', '', '3.3',b'00000','2');
insert into pages value ('34', 'Вей Усянь', 'Глава 4. Нападение. Часть вторая', '4.1',b'00001','2');
insert into pages value ('35', 'Вей Усянь', '', '4.2',b'00000','2');
insert into pages value ('36', 'Вей Усянь', '', '4.3',b'00000','2');

insert into pages value ('37', 'Мэй Чансу', 'Глава 1', '1.1',b'00001','5');
insert into pages value ('38', 'Мэй Чансу', 'Глава 2', '2.1',b'00001','5');
insert into pages value ('39', 'Мэй Чансу', '', '2.2',b'00000','5');
insert into pages value ('40', 'Мэй Чансу', 'Глава 3', '3.1',b'00001','5');
insert into pages value ('41', 'Мэй Чансу', 'Глава 4', '4.1',b'00001','5');


insert into t_user (id, active, password, username) value ('1', b'00001', '$2a$10$Iw.pUB5E00zP3VB.ISijKuWfTKbCxAnNTeotPaqjbATh8gsP.AkgO', 'Admin');
insert into t_roles value ('1', 'ADMIN');
insert into t_user (id, active, password, username) value ('2', b'00001', '$2a$10$XFCF9gNvd6dwWs.SEkRNp.GWfNFSaT/vGQxoBLt1HG9VAUwnUcAP2', 'editor');
insert into t_roles value ('2', 'MODERATOR');