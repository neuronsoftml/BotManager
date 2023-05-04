# BotManager Telegram bot На озброєння має наступні функції.
1) Виводить курс валют, використовуючи запит на НБУ та здійснює парсинг файла json.
2) Режим криптографії "Цезарь" EncryptionMod.class.

Володіє наступними навиками:
  Шифрує повідомлення. (Доступні мови Українська, Англійська).
  Розшифровує повідомлення. (Доступні мови Українська, Англійська).
  Здійснює BRUT-FORCE Атаку. (Доступні мови Українська).
 
Текст для опрацювання приймає в двох видах.
- Текст повідомлення.
- Текст розміщений у файл.
          
Відповідь здійснює у одному виді.
- Текст повідомлення.
- Генерує файл з відповіддю.
- Функція надіслання файла не реалізована, через необхідність додаткового файлового сервера. 
Обмеження Telegram API та як відправити файл з локального диску у відповідь користувачу не можна.

Визначає мову тексту:
- Доступ встановити мову тексту.
- У випадку неправильного встановлення бот автоматично визначає мову тексту.

Встановлення ключа.
- Доступ до встановлення ключа в межі діапазонна.
- У випадку виходу за діапазон чисел, бот автоматично генерує правильний ключ.

BRUT-FORCE (Доступний тільки для Української мови.)
- Здійснює атаку методом підпору ключа в межі діапазону визначеної мови тексту.
- Статичний аналіз, здійснюється методом аналізу правильних складених слів.


!!! Увага !!! для запуску бато на вашому пристрої зверніть увагу на конфіг файли, а саме: 
  BotConfig
      BOT_NAME("Ім'я бота"),
      BOT_TOKEN("Токен бота");
 
 ConfigFileUrl
    - необхідно вказати шлях до файлів, що розміщено в папці .\db\ukraine  
    DOWNLOAD_FILE - шлях до папки де буде зберігатися файли, які відправив користувач.
    SEND_FILE - шлях до папки де буде зберігатися файли, які готові до відправки користувачу.

