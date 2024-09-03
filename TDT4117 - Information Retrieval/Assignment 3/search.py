
# imports for assignment
import random; random.seed(123)
import codecs
import string
from nltk.stem.porter import PorterStemmer
import gensim 

# function to preprocess documents and queries
def preprocess(document):
    # lower characters and tokenize document
    document = document.lower().split()

    # remove punctuation
    document = [w.strip(string.punctuation) for w in document]

    # stem words to root forms
    stemmer = PorterStemmer()
    document = [stemmer.stem(w) for w in document]

    return document


# open, read and split file into paragraphs
f = codecs.open("pg3300.txt", "r", "utf-8")
document_collection = f.read().split("\n\n")

# remove any pargraphs containing the word "Gutenberg" 
document_collection = [i for i in document_collection if "Gutenberg" not in i]

# copying the original documents in collection for displaying them after the search
document_collection_copy = document_collection

# preprocess paragraphs
document_collection = [preprocess(paragraph) for paragraph in document_collection]


# building a dictionary defining every word in the collection
# maps to a gensim Dictionary object containing all unique words
dictionary = gensim.corpora.Dictionary(document_collection)


# list of stopwords to remove from the documents
stop_words = ['i','me','my','myself','we','our','ours','ourselves','you','your','yours','yourselves','he', 'him', 'his', 'himself', 'she', 'her', 'hers', 'herself', 'it', 'its', 'itself', 'they', 'them', 'their', 'theirs', 
'themselves', 'what', 'which', 'who', 'whom', 'this', 'that', 'these', 'those', 'am', 'is', 'are', 'was', 'were', 'be', 'been', 'being', 'have', 'has', 'had', 'having', 'do', 'does', 'did',
'doing', 'a', 'an', 'the', 'and', 'but', 'if', 'or', 'because', 'as', 'until', 'while', 'of', 'at', 'by', 'for', 'with', 'about', 'between', 'into', 'through', 'during', 'before', 'after',
'above', 'below', 'to', 'from', 'up', 'down', 'in', 'out', 'on', 'off', 'over', 'under', 'again', 'further', 'then', 'once', 'here', 'there', 'when', 'where', 'why', 'how', 'all', 'any',
'both', 'each', 'few', 'more', 'most', 'other', 'some', 'such', 'no', 'nor', 'not', 'only', 'own', 'same', 'so', 'than', 'too', 'very', 's', 't', 'can', 'will', 'just', 'don', 'should', 'now']

# give tokens in the dictionary a unique integer id for lookup
token2id = dictionary.token2id

# add stopword's id to stopwords_ids if they exist in the document collection
stopword_ids = [token2id[stopword] for stopword in stop_words if stopword in token2id]

# remove stopwords defined above from the dictionary
dictionary.filter_tokens(stopword_ids)

# create a corpus of BOW of the document collection, maps every word's ID and its frequency in the collection i.e word 46 appears 621 times: (46, 621)
corpus = [dictionary.doc2bow(paragraph) for paragraph in document_collection]


# map BOW to TF-IDF weights where every word ID is mapped in a tuple together with its TF-IDF weight
tfidf_model = gensim.models.TfidfModel(corpus)
tfidf_corpus = tfidf_model[corpus]

# create similarity index to compare documents to queries
tfidf_similarity = gensim.similarities.MatrixSimilarity(tfidf_corpus)


# map to lsi topic weights
lsi_model = gensim.models.LsiModel(tfidf_corpus, id2word = dictionary, num_topics = 100)
lsi_corpus = lsi_model[corpus]
lsi_similarity = gensim.similarities.MatrixSimilarity(lsi_corpus)


# query and query preprocessing
query = "How taxes influence Economics?"
query_copy = query
query = preprocess(query)
query = dictionary.doc2bow(query)


# create a vector of tfidf weights for the query
vec_tfidf = tfidf_model[query]


# map query to lsi topic weights and sort by similarity to find best matches
doc2similarity = enumerate(tfidf_similarity[vec_tfidf])
output = sorted(doc2similarity, key = lambda kv: -kv[1])[:3]


# print results
print(f'\nTop three results for query "{query_copy}"\n')
for document in output:
    print(f'\n{document_collection_copy[document[0]]}\n')


# map query to lsi topic weights and sort by similarity to find top topics
lsi_query = lsi_model[query]
lsi_query_sorted = sorted(lsi_query, key = lambda kv: -abs(kv[1]))[:3]
doc2similarity = enumerate(lsi_similarity[lsi_query])
doc2similarity_sorted =  sorted(doc2similarity, key = lambda kv: -kv[1])[:3]


# print results
for topic in lsi_query_sorted:
    print(f'\n{lsi_model.show_topics()[topic[0]][1]}\n')