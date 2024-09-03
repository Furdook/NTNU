import pandas as pd
import numpy as np
from google_pygram import GooglePyGram as gpg

def function(start_year, end_year, phrases, drop_coloumns):
    pygram = gpg(
        corpus = 'English',
        corpus_year = 2019,
        start_year = start_year,
        end_year = end_year,
        smoothing = 3,
        case_sensitive = False,
        phrases = phrases
    )
    ngram = pygram.to_df()
    ngram = ngram.drop(drop_coloumns)
    ngram.plot(x = "year")

