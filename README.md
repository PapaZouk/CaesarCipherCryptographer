<picture><img src="https://user-images.githubusercontent.com/106058501/210579242-d4b840e9-601a-432b-b21b-b63b37f8a587.png" alt="Caesar Cipher Crypthographer" width="30"/></picture> Caesar Cipher Cryptographer 
--------------------------------
Author: Rafal Papala

## Project Conspect:
* Goal
* Definition
* Used tools and languages
* Methods
* 
<picture><img src="https://user-images.githubusercontent.com/106058501/210584218-c1514df8-49e3-4f49-8c7b-42a5292e1e42.png" alt="Goal" width="30"/></picture> Goal
--------------------------------
Caesar Cipher Cryptographer program was made as a final project for CodeGym University course between October 2022 and January 2023.
The general idea was to use gained knowladge about Java Fundamentals and to write a program that encrypt or decrypt provided file in .txt format
using Caesar cipher method by providing key number to shifts letters.

## Caesar cipher definition:
>In cryptography, a Caesar cipher, also known as Caesar's cipher, the shift cipher, Caesar's code or Caesar shift, 
>is one of the simplest and most widely known encryption techniques. It is a type of substitution cipher in which 
>each letter in the plaintext is replaced by a letter some fixed number of positions down the alphabet. For example, 
>with a left shift of 3, D would be replaced by A, E would become B, and so on. The method is named after Julius Caesar, 
>who used it in his private correspondence.

Source: https://en.wikipedia.org/wiki/Caesar_cipher

## Used tools and languages
This program was build using Intellij IDEA and writen in Java language.
<picture>
  <p>
  <img src="https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ficons.iconarchive.com%2Ficons%2Fpapirus-team%2Fpapirus-apps%2F128%2Fintellij-icon.png&f=1&nofb=1&ipt=2e43a8f182375c8b2d746ae40ca9f55a39d93487840632ac4960f61981351b28&ipo=images" alt="Intellij IDEA" width="30"/>
  <img src="https://external-content.duckduckgo.com/iu/?u=http%3A%2F%2Fwww.pngall.com%2Fwp-content%2Fuploads%2F2016%2F05%2FJava-PNG-Image-180x180.png&f=1&nofb=1&ipt=58ed3b0ab447fadf33cacd2be570d6bb1410cad517baebfe85d8affc645248ac&ipo=images" alt="Java" width="30"/>
  </p>
</picture>

## Methods:
1. Encryption - client encrypt file by providing path to file on local storage and by giving positive integer to shift letters to the right.
   The output file ends with suffix "_encrypted".
2. Decryption - same as above, but this time client choose positive integer to decrypt file.
   The output file ends with suffix "_decrypted".
3. Cryptanalysis - client has to choose on of the following options:
    * Brute force method - client provides path to the file on local storage and program is trying all possible keys until he find correct one.
    * Statistic analysis - this method gather statistics about frequency of all characters from two files: encrypted file and second file 
      provided by client which content is similar to the first file before encryption. Then program tries to decrypt content by shifting letters 
      by the difference of the letters.

