import random
unused_names = ['Abraham Lincoln', 'Adolf Hitler', 'Al Gore', 'Albert Einstein', 'Alexander the Great', 'Alfred Hitchcock', 'Amelia Earhart', 'Angelina Jolie', 'Anne Frank', 'Audrey Hepburn', 'Babe Ruth', 'Barak Obama', 'Benjamin Franklin', 'Beyonce', 'Bill Gates', 'Brad Pitt', 'C.S. Lewis', 'Carl Lewis', 'Charles Darwin', 'Charles De Gaulle', 'Christopher Columbus', 'Cleopatra', 'Cristiano Ronaldo', 'Dalai Lama', 'David Beckham', 'David Cameron', 'Donald Trump', 'Elvis Presley', 'Ernest Hemingway', 'Florence Nightingale', 'Franklin D. Roosevelt', 'Gabby Douglas', 'Galileo Galilei', 'George Clooney', 'George Orwell', 'George Washington', 'Her Majesty, Queen Elizabeth II', 'Hillary Clinton', 'J.R.R. Tolkien', 'Jesus', 'JK Rowling', 'Joan of Arc', 'John F. Kennedy', 'John Lennon', 'John Stewart', 'Joseph Stalin', 'Julie Andrews', 'Karl Marx', 'Kate Middleton',
                'Lady Gaga', 'Lance Armstrong', 'Lionel Messi', 'Ludwig Van Beethoven', 'Madonna', 'Mahatma Ghandi', 'Malala Yousafzai', 'Marie Antoinette', 'Marie Curie', 'Marilyn Monroe', 'Mark Zuckerberg', 'Martin Luther King Jr.', 'Micheal Jackson', 'Micheal Jordan', 'Micheal Phelps', 'Mother Teresa', 'Muhammed Ali', 'Napoleon Bonaparte', 'Neil Armstrong', 'Nelson Mandela', 'Oprah Winfrey', 'Orville Wright', 'Pablo Picasso', 'Paul McCartney', 'Pele', 'Plato', 'Pope Francis', 'Pope John Paul II', 'Princess Diana', 'Queen Victoria', 'Rodger Federer', 'Rosa Parks', 'Salvador Dali', 'Shakira', 'Simon Bolivar', 'Sir Isaac Newton', 'Stephen Hawking', 'Steve Jobs', 'Sting', 'Thomas Jefferson', 'Tom Cruise', 'Tony Blair', 'Usain Bolt', 'Vincent Van Gogh', 'Vladimir Putin', 'Walt Disney', 'Warren Buffett', 'Will Smith', 'William Shakespeare', 'Winston Churchill']
used_names = []


def get_name():
    random_number = random.randint(0, len(unused_names))
    used_names.append[unused_names.pop(random_number)]


def release_name(name):
    used_names.remove(name)
    unused_names.append(name)
