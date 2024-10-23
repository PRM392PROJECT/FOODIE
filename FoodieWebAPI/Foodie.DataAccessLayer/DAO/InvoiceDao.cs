using Foodie.DataAccessLayer.DBContexts;
using Foodie.DataAccessLayer.Models;
using Microsoft.EntityFrameworkCore;
using System;
using System.Collections.Generic;
using System.Linq;

namespace Foodie.DataAccessLayer.DAO
{
    public class InvoiceDao 
    {
        private readonly FOODIEContext _context;

        public InvoiceDao(FOODIEContext context)
        {
            _context = context;
        }

        public async Task<int> Count()
        {
            return await _context.Invoices.CountAsync();
        }

        public async Task<Invoice> Create(Invoice entity)
        {
            _context.Invoices.Add(entity);
            _context.SaveChangesAsync();
            return entity;
        }

        public async Task<bool> Delete(Invoice entity)
        {
            _context.Invoices.Remove(entity);
            return await _context.SaveChangesAsync() > 0;
        }

        public async Task<bool> DeleteById(int id)
        {
            var invoice = _context.Invoices.FirstOrDefault(i => i.InvoiceId == id);
            if (invoice != null)
            {
                _context.Invoices.Remove(invoice);
                return await _context.SaveChangesAsync() > 0;
            }
            return false;
        }

        public async Task<IEnumerable<Invoice>> GetAll()
        {
            return await _context.Invoices.ToListAsync();
        }

        public async Task<Invoice> GetById(int id)
        {
            return await _context.Invoices.FirstOrDefaultAsync(i => i.InvoiceId == id);

        }

        public async Task<bool> Update(Invoice entity)
        {
            _context.Invoices.Update(entity);
            return await _context.SaveChangesAsync() > 0;
        }
    }
}
