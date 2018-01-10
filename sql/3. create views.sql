/* незаблокированные(активные) абоненты */
CREATE VIEW vw_active_subscribers_id (`id`)
	AS SELECT `id` FROM `users` WHERE `role` = 'SUBSCRIBER' AND isActive = true;

/* деталированная статистика по звонкам абонентов за текущий месяц */
CREATE VIEW vw_mon_calls_detailed (`subscriberID`, `rateType`, `duration`, `tariff`) AS
SELECT c.`subscriberID`,
    r.`type` AS rateType,
    SUM(TIMESTAMPDIFF(SECOND, c.`begin`, c.`finish`)) AS duration,
    r.`tariff`
FROM `calls` AS c
INNER JOIN `rates` AS r ON r.`id` = c.`rateID`
WHERE (`begin` >= LAST_DAY(CURDATE()) + INTERVAL 1 DAY - INTERVAL 1 MONTH
    AND `finish` < LAST_DAY(CURDATE()) + INTERVAL 1 DAY)
GROUP BY c.`subscriberID`, r.`type`, r.`tariff`;

/* стоимость звонков абонента за текущий месяц*/
CREATE VIEW vw_mon_calls_cost(`subscriberID`, `sum`) AS
SELECT `subscriberID`, SUM(duration * tariff / 60) AS `sum`
FROM vw_mon_calls_detailed
GROUP BY `subscriberID`;

/* стоимость подключения услуг абонентом в текущем месяце */
CREATE VIEW vw_mon_subscriptions_cost(`subscriberID`, `sum`) AS
SELECT sb.`subscriberID`, SUM(o.`subscriptionRate`) AS `sum`
FROM `subscriptions` AS sb
INNER JOIN `offers` AS o
    ON sb.`offerID` = o.`id`
WHERE (`connected` >= LAST_DAY(CURDATE()) + INTERVAL 1 DAY - INTERVAL 1 MONTH)
    AND sb.`subscriberID` IN(SELECT `id` FROM vw_active_subscribers_id)
GROUP BY sb.`subscriberID`;

/* абонентская плата за действующие услуги в текущем месяце */
CREATE VIEW vw_mon_srv_fee(`subscriberID`, `sum`) AS
SELECT sb.`subscriberID`, SUM(o.`monthlyFee`) AS `sum`
FROM `subscriptions` AS sb
INNER JOIN `offers` AS o ON sb.`offerID` = o.`id`
WHERE (`disconnected` IS NULL
    OR (`disconnected` >= LAST_DAY(CURDATE()) + INTERVAL 1 DAY - INTERVAL 1 MONTH))
    AND `subscriberID` IN(SELECT `id` FROM vw_active_subscribers_id)
GROUP BY sb.`subscriberID`;

/* обязательная абонентская плата за услуги электросвязи */
CREATE VIEW vw_mon_major_fee(`subscriberID`, `sum`) AS
SELECT s.`id` AS `subscriberID`, SUM(o.`monthlyFee`) AS `sum`
FROM `offers` AS o
JOIN  vw_active_subscribers_id AS s ON 1=1
WHERE `required` = true
GROUP BY s.`id`;