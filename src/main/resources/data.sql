drop table if exists main.point_questions;

create table main.point_questions
(
    id                bigint not null
        primary key,
    correct_answer    varchar(255),
    next_point        varchar(255),
    point_code        varchar(255),
    point_coordinates varchar(255),
    point_name        varchar(255),
    point_question    varchar(255)
);

insert into main.point_questions (id, correct_answer, next_point, point_code, point_coordinates, point_name, point_question) values (1, 'C', '260920', '260621', '53.251482, 50.222907', 'Самолет', 'Ил-2, сбитый на Карельском перешейке, установлен здесь в 1975 как символ города и авиационной промышленности в годы войны.
Один из этих велосипедов изготовлен из алюминия 6061, который также используется в авиапромышленности, КАКОЙ?

A. Cinelli Tutto
B. Dosnoventa Detroit
C. Aventon Cordoba
D. Engine11 Crit-D');
insert into main.point_questions (id, correct_answer, next_point, point_code, point_coordinates, point_name, point_question) values (2, 'A', '060522', '260920', '53.212406, 50.144911', 'Ракета', 'Это настоящая ракета, собранная здесь в Самаре в 1984 году. Служила она для тренировок боевых расчетов на космодроме Плесецк. Сам монумент ракеты открылся в 2001 году, а далее в 2007 открылся музей «Самара космическая».
Какой из этих компонентов никогда не производился брендом RAKETA?

A. Raketa Superpista Сhainring
B. Raketa Aeroring Сhainring
C. Raketa Raw Chainring
D. Raketa Diamondring Сhainring');
insert into main.point_questions (id, correct_answer, next_point, point_code, point_coordinates, point_name, point_question) values (3, 'C', '171020', '060522', '53.215811, 50.132616', 'Ладья', 'Стела "Ладья" была открыта вместе с 4-й очередью набережной в рамках празднования 400-летия города в 1986 г. Почему ладья? По легенде они ходили по всему течению Волги еще задолго до основания Самары, когда отряды из Руси отправлялись по пути "из варяг в греки".
Есть такой вело маршрут Tour Unite, по эпичности не уступает пути "из варяг в греки". Вопрос - где он проходит?

A. От Северного моря до Каспийского
B. От Балтийского моря до Азовского
C. От Баренцева моря до Черного
D. От Норвежского моря до Средиземного');
insert into main.point_questions (id, correct_answer, next_point, point_code, point_coordinates, point_name, point_question) values (4, 'B', '290423', '171020', '53.204884, 50.108491', 'Пл. Славы', 'Пл. Славы построена на Самарской площади города на месте Ярмарочного спуска в 1971 году. А Монумент Славы — символ вклада самарцев в становление авиационной промышленности России.
Ладно, давай к вопросам полегче, нуу, например, сколько весит фреймсет TSUNAMI SNM100?

A. 2100
B. 2200
C. 2300
D. 2400');
insert into main.point_questions (id, correct_answer, next_point, point_code, point_coordinates, point_name, point_question) values (5, 'D', '230722', '290423', '53.201326, 50.099748', 'Пивзавод', 'Жигулёвский пивоваренный завод — один из старейших пивоваренных заводов России, был построен в 1881 году австрийским подданным Альфредом Филипповичем фон Вакано. Сейчас на заводе производится 13 видов пенного.
А сколько видов нужно было продегустировать на первом Жигуллейкате?

A. 7
B. 4
C. 6
D. 5');
insert into main.point_questions (id, correct_answer, next_point, point_code, point_coordinates, point_name, point_question) values (6, 'D', '240421', '230722', '53.194722, 50.100375', 'Пл. Куйбышева', 'Ты думал самая большая площадь России находится в Москве, а вот не угадал, она у нас здесь - пл. Куйбышева.
Площадь рекордсмен, ладно это ты мог и не знать, но раз ты доехал сюда на веле, то это точно должен - кому принадлежит рекорд в индивидуальной гонке преследования на 4 км?

A. Крис Хой
B. Денис Дмитриев
C. Симон Консонни
D. Филиппо Ганна');
insert into main.point_questions (id, correct_answer, next_point, point_code, point_coordinates, point_name, point_question) values (7, 'C', '240922', '240421', '53.186875, 50.121409', 'Ж\Д Вокзал', 'Вокзал, построенный в 1999-2001 г. на смену старого вокзала является самым высоким среди вокзалов Европы.
Продолжим про рекорды, какой в данный момент часовой рекорд езды (рекордное расстояние, которое способен проехать велосипедист за 1 час)?

A. 49 700 м
B. 54 526 м
C. 56 792 м
D. 58 313 м');
insert into main.point_questions (id, correct_answer, next_point, point_code, point_coordinates, point_name, point_question) values (8, 'A', '180720', '240922', '53.185214, 50.087521', 'Пл. Революции', 'До революции здесь стоял памятник Александру II, но после он решил уступить свое место В. И. Ленину.
Спортивные рекорды надоели, поэтому вопрос про рекорды советские - какие модели были у ХВЗ "Рекорд"?

A. В-64, 156-421
B. В67И, 156-421
C. В-64, 153—422
D. В67И, 155-461');
insert into main.point_questions (id, correct_answer, next_point, point_code, point_coordinates, point_name, point_question) values (9, 'B', '240520', '180720', '53.177590, 50.074598', 'Крепость', 'Датой основания города считается 1586 год, когда начала строиться крепость. В 2017 построили эту башню, которая является символом той крепости.
А где же зарождался фиксед гир, в какой стране он выбрался с трека на улицу первым?

A. Франция
B. Япония
C. США
D. Англия');
insert into main.point_questions (id, correct_answer, next_point, point_code, point_coordinates, point_name, point_question) values (10, 'D', null, '240520', '53.189009, 50.097814', 'Финиш! Рюмочная Боря. Газуй!', 'Как ты меня увидел? Тогда давай дополнительный вопрос тебе.
В каком году было впервые выпущено это произведение искусства Cinelli Laser Pista?

A. 2001
B. 1975
C. 1991
D. 1984');
