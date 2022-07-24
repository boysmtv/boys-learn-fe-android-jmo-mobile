package com.boys.assets.jmo.helper

import android.os.Build
import androidx.annotation.RequiresApi
import com.boys.assets.jmo.utils.LogUtil
import java.security.InvalidKeyException
import java.security.KeyFactory
import java.security.NoSuchAlgorithmException
import java.security.interfaces.RSAPrivateKey
import java.security.interfaces.RSAPublicKey
import java.security.spec.InvalidKeySpecException
import java.security.spec.PKCS8EncodedKeySpec
import java.security.spec.X509EncodedKeySpec
import java.util.*
import javax.crypto.BadPaddingException
import javax.crypto.Cipher
import javax.crypto.IllegalBlockSizeException
import javax.crypto.NoSuchPaddingException

class RSAUtils {

    companion object {
        /**
        }
         * encryption algorithm
         */
        const val TAG = "RSAUtils"
        const val RSA = "RSA"
        const val RSA_ECB_PKCS1PADDING = "RSA/ECB/PKCS1Padding"

        /**
         * the keys
         */
        private const val publicKey =
            "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCgFGVfrY4jQSoZQWWygZ83roKXWD4YeT2x2p41dGkPixe73rT2IW04glagN2vgoZoHuOPqa5and6kAmK2ujmCHu6D1auJhE2tXP+yLkpSiYMQucDKmCsWMnW9XlC5K7OSL77TXXcfvTvyZcjObEz6LIBRzs6+FqpFbUO9SJEfh6wIDAQAB"
        private const val privateKey = "" // a private key should be private

        @RequiresApi(Build.VERSION_CODES.O)
        @Throws(NoSuchAlgorithmException::class, InvalidKeySpecException::class)
        fun decodePublicKey(base64PublicKey: String?): RSAPublicKey {
            val keyBytes = Base64.getDecoder().decode(base64PublicKey)
            val keySpec = X509EncodedKeySpec(keyBytes)
            val keyFactory = KeyFactory.getInstance(RSA)
            return keyFactory.generatePublic(keySpec) as RSAPublicKey
        }

        @RequiresApi(Build.VERSION_CODES.O)
        @Throws(NoSuchAlgorithmException::class, InvalidKeySpecException::class)
        fun decodePrivateKey(base64PrivateKey: String?): RSAPrivateKey {
            val keyBytes = Base64.getDecoder().decode(base64PrivateKey)
            val keySpec = PKCS8EncodedKeySpec(keyBytes)
            val keyFactory = KeyFactory.getInstance(RSA)
            return keyFactory.generatePrivate(keySpec) as RSAPrivateKey
        }

        @RequiresApi(Build.VERSION_CODES.O)
        @Throws(
            InvalidKeySpecException::class,
            NoSuchAlgorithmException::class,
            InvalidKeyException::class,
            BadPaddingException::class,
            IllegalBlockSizeException::class,
            NoSuchPaddingException::class
        )
        fun encryptText(content: String): String {
            val contentBytes = content.toByteArray()
            val cipher = Cipher.getInstance(RSA_ECB_PKCS1PADDING)
            cipher.init(Cipher.ENCRYPT_MODE, decodePublicKey(publicKey))
            val cipherContent = cipher.doFinal(contentBytes)
            return Base64.getEncoder().encodeToString(cipherContent)
        }

        @RequiresApi(Build.VERSION_CODES.O)
        fun decryptText(cipherContent: String): String? {
            val cipherContentBytes = Base64.getDecoder().decode(cipherContent.toByteArray())
            val cipher: Cipher
            try {
                cipher = Cipher.getInstance(RSA_ECB_PKCS1PADDING)
                cipher.init(Cipher.DECRYPT_MODE, decodePrivateKey(privateKey))
                val decryptedContent = cipher.doFinal(cipherContentBytes)
                return String(decryptedContent)
            } catch (e: NoSuchAlgorithmException) {
                LogUtil.e(TAG, e.localizedMessage)
            } catch (e: NoSuchPaddingException) {
                LogUtil.e(TAG, e.localizedMessage)
            } catch (e: InvalidKeyException) {
                LogUtil.e(TAG, e.localizedMessage)
            } catch (e: InvalidKeySpecException) {
                LogUtil.e(TAG, e.localizedMessage)
            } catch (e: IllegalBlockSizeException) {
                LogUtil.e(TAG, e.localizedMessage)
            } catch (e: BadPaddingException) {
                LogUtil.e(TAG, e.localizedMessage)
            }
            return null
        }
    }
}