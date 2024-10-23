using Foodie.Service.Models;
using Microsoft.Extensions.Configuration;
using System;
using System.IO;
using System.Threading.Tasks;

namespace Foodie.Service.FileManager
{
    public class FileService : IFileService
    {
        private readonly string _urlFolder;

        public FileService(IConfiguration configuration)
        {
            _urlFolder = configuration["UploadsFolder"];
        }

        public async Task<bool> DeleteFile(string filePath)
        {
            if (File.Exists(filePath))
            {
                File.Delete(filePath);
                return true;
            }
            return false;
        }

        public async Task<FileUploadViewModel> SaveFile(FileUploadViewModel file)
        {
            if (file.File == null || file.File.Length == 0)
                throw new ArgumentException("Invalid file.");

            // Tạo tên file duy nhất
            var fileName = $"{Guid.NewGuid()}_{file.File.FileName}";
            var fullPath = Path.Combine(_urlFolder, fileName);

            // Tạo thư mục nếu chưa tồn tại
            if (!Directory.Exists(_urlFolder))
            {
                Directory.CreateDirectory(_urlFolder);
            }

            // Lưu file lên server
            using (var stream = new FileStream(fullPath, FileMode.Create))
            {
                await file.File.CopyToAsync(stream);
            }

            // Trả về model bao gồm đường dẫn file
            file.FilePath = fullPath;
            return file;
        }

        public async Task<FileUploadViewModel> UpdateFile(FileUploadViewModel file)
        {
            // Xóa file cũ trước khi cập nhật
            if (!string.IsNullOrEmpty(file.FilePath))
            {
                await DeleteFile(file.FilePath);
            }

            // Lưu file mới
            return await SaveFile(file);
        }
    }
}
