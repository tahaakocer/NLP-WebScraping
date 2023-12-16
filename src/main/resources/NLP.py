import os
import docx
import re
import pandas as pd
from nltk import sent_tokenize
from nltk import word_tokenize
from nltk.corpus import stopwords

def remove_numerics(sentence):
    return re.sub(r'\d+', '', sentence)

def remove_special_characters(sentence):
    return re.sub(r'[^a-zA-Z0-9\s]', '', sentence)

def cumlelere_ayir(doc):
    sentences = []
    for paragraph in doc.paragraphs:
        sentences.extend(sent_tokenize(paragraph.text))
    return sentences

def cumleleri_excel_yaz(doc, excel_path):
    sentences = cumlelere_ayir(doc)
    df = pd.DataFrame(sentences, columns=["Cümle"])
    df.to_excel(excel_path, index=False)
    print(f"Cümleler Excel dosyasına aktarıldı: {excel_path}")
    return df  # Return the DataFrame

def remove_stopwords(sentence):
    stop_words = set(stopwords.words('english'))  # Dil olarak 'english' kullanabilirsiniz, veya projenize uygun bir dil seçebilirsiniz.
    words = word_tokenize(sentence)
    filtered_sentence = [word for word in words if word.lower() not in stop_words]
    return ' '.join(filtered_sentence)

def process_and_save_to_excel(input_excel_path, output_excel_filename):
    # Excel dosyasını oku
    df = pd.read_excel(input_excel_path)

    # Cümleleri temizle
    df['Cümle'] = df['Cümle'].apply(remove_numerics)
    df['Cümle'] = df['Cümle'].apply(remove_special_characters)
    df['Cümle'] = df['Cümle'].apply(remove_stopwords)
    df['Cümle'] = df['Cümle'].str.lower()

    # Güncellenmiş cümleleri yeni Excel dosyasına yaz
    output_excel_path = os.path.join(os.getcwd(), output_excel_filename)
    df.to_excel(output_excel_path, index=False)

    print(f"Güncellenmiş cümleler Excel dosyasına aktarıldı: {output_excel_path}")
    

# Kullanım örneği

def main():
    # DOCX dosyasını aç
    doc_path = "C:\\Users\\tahat\\Desktop\\deneme.docx"
    doc = docx.Document(doc_path)
    excel_path = "C:\\Users\\tahat\\eclipse-workspace\\nlp-web-scraping\\src\\main\\resources\\output.xlsx"
    output_excel_filename = "output_cleaned.xlsx"  # Oluşturulacak temizlenmiş Excel dosyasının adı
    
    cumleleri_excel_yaz(doc, excel_path)
    
    process_and_save_to_excel(excel_path, output_excel_filename)

if __name__ == "__main__":
    main()
