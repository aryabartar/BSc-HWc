import numpy as np
import pandas as pd

train_df = pd.read_csv('train.csv')
test_df = pd.read_csv('test.csv')
correct_answer_df = pd.read_csv("answers.csv", header=None)
train_df.head(2)

train_arr = np.array(train_df)
test_arr = np.array(test_df)

data_size = len(train_arr)
number_of_spam = 0
for i in range(0, data_size):
    if train_arr[i][-1] == 1:
        number_of_spam += 1

number_of_not_spam = data_size - number_of_spam

spam_probability = number_of_spam / data_size
not_spam_probability = 1 - spam_probability
print("Spam Probability:", spam_probability)

element_spam_probability = [0] * len(train_arr[0])
element_not_spam_probability = [0] * len(train_arr[0])
not_element_spam_probability = [0] * len(train_arr[0])
not_element_not_spam_probability = [0] * len(train_arr[0])

for i in range(0, data_size):
    for j in range(0, len(train_arr[0])):
        if train_arr[i][-1] == 1 and train_arr[i][j] == 1:
            element_spam_probability[j] += 1
        if train_arr[i][-1] == 0 and train_arr[i][j] == 1:
            element_not_spam_probability[j] += 1

n = len(test_arr)
predict = np.zeros(n)
for i in range(0, n):  # for each data in test set:
    spm = 0.0
    nt_spm = 0.0
    spm = spam_probability
    nt_spm = not_spam_probability

    for j in range(0, len(test_arr[i] - 1)):
        if test_arr[i][j] == 1:
            spm *= (element_spam_probability[j] / number_of_spam)
            nt_spm *= (element_not_spam_probability[j] / number_of_not_spam)
        else:
            spm *= ((number_of_spam - element_spam_probability[j]) / number_of_spam)
            nt_spm *= ((number_of_not_spam - element_not_spam_probability[j]) / number_of_not_spam)

    if spm > nt_spm:
        predict[i] = 1
    else:
        predict[i] = 0

output = pd.DataFrame(predict)
output.to_csv("SPM_9524014.csv", index=False, header=False)

diff = (np.asarray(output) == np.asarray(correct_answer_df))
accuracy = np.sum(diff) / len(diff)
print("Accuracy:", accuracy)