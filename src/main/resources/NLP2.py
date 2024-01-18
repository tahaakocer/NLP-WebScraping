# -*- coding: utf-8 -*-
"""
Created on Tue Jan 16 23:47:38 2024

@author: tahat
"""

import pandas as pd
from gensim.models import Word2Vec
from nltk.tokenize import word_tokenize

df = pd.read_excel('output_cleaned.xlsx')
sentences = df['Cümle'].tolist()

corpus = []
for sentence in sentences:
    # Check if the value is a string before splitting
    if isinstance(sentence, str):
        corpus.append(sentence.split())
    else:
        # Handle other types (e.g., convert to string or use a different strategy)
        corpus.append([])
        
print(len(corpus))
print(len(sentences))

model = Word2Vec(corpus,vector_size=100,window=5,min_count=5,sg=1)

print(model.wv['good'])